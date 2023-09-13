package com.example.blackburger.network.interceptor

import android.content.Context
import com.example.blackburger.log.AppLog
import com.example.blackburger.network.api.NetworkDataSource
import com.laposte.surfngfacteo.core_common.constant.EXPIRATION_KEY
import com.laposte.surfngfacteo.core_common.constant.PropertiesManager.getBuildConfigValue
import com.laposte.surfngfacteo.core_common.constant.TOKEN_KEY
import com.laposte.surfngfacteo.core_common.constant.UID_KEY
import com.example.blackburger.repository.PreferencesRepository
import com.example.blackburger.network.const.AUTHORIZATION
import com.example.blackburger.network.const.CONTENT_TYPE
import com.example.blackburger.network.const.MIME
import com.example.blackburger.network.const.TOKEN_ENDPOINT
import com.example.blackburger.network.const.UID
import com.example.blackburger.network.const.VERSION
import com.example.blackburger.network.const.VERSION_VALUE
import com.example.blackburger.network.model.response.TokenApimResponse
import kotlinx.coroutines.runBlocking
import okhttp3.Credentials.basic
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor(
    private val context: Context,
    private val preferencesRepository: PreferencesRepository,
    private val networkDataSource: NetworkDataSource
) : Interceptor {

    var savedUID: String? = null
    var savedToken: String? = null
    var savedExpirationToken: Long? = null
    private var tokenApimResponse: TokenApimResponse? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        AppLog.logDebutMethode(this, "intercept()")

        runBlocking {
            savedUID = preferencesRepository.readString(UID_KEY, "")
        }

        return (if (isApimActive()) {
            var tokenError = false
            var tokenResponse: Response? = null
            runBlocking {
                if (TOKEN_ENDPOINT != chain.request().url.toUrl().path) {
                    AppLog.debug(this, "url is not /token")
                    loadExistingToken()
                    if (isExpiredToken()) {
                        getNewToken().let {
                            tokenResponse = it
                            tokenError = !it.isSuccessful
                        }
                    }
                }
            }

            if (tokenError) {
                tokenResponse!!
            } else {
                val newRequest = addHeaderOnRequest(chain)
                chain.proceedToLoggingOnError(newRequest, chain)
            }
        } else {
            chain.proceed(
                chain.request().newBuilder()
                    .universalHeader()
                    .addHeader("habileo", "habileoAuthentification")
                    .build()
            )
        }).also {
            AppLog.logFinMethode(this, "intercept()")
        }
    }

    private fun isExpiredToken(): Boolean {
        AppLog.logDebutMethode(this, "isExpiredToken()")

        return if (savedExpirationToken != null && savedExpirationToken != 0L) {
            AppLog.debug(this, "token different of 0L and null")
            val isExpired = (System.currentTimeMillis() / 1000) > savedExpirationToken!!
            AppLog.debug(this, "token is expired ? $isExpired")
            isExpired
        } else {
            AppLog.debug(this, "token is expired")
            true
        }

    }

    private suspend fun getNewToken(): Response {
        AppLog.logDebutMethode(this, "getNewToken()")
        val result = networkDataSource.getToken()
        if (result.isSuccessful && result.body() != null) {
            tokenApimResponse = result.body()
            AppLog.debug(
                this, "tokenResponse is not null, so " +
                        "we save the new token"
            )
            saveNewToken(
                tokenApimResponse!!.mAccessToken.toString(),
                tokenApimResponse!!.mExpiresIn
            )
            loadExistingToken()
        } else {
            AppLog.debug(this, "tokenResponse get new token error. Error: ${result.message()} -> ${result.errorBody()?.string()}")
        }
        return result.raw()
    }

    private suspend fun loadExistingToken() {
        AppLog.logDebutMethode(this, "loadExistingToken")
        savedToken = preferencesRepository.readString(TOKEN_KEY, "")
        savedExpirationToken = preferencesRepository.readLong(EXPIRATION_KEY, 0)
        AppLog.logFinMethode(this, "loadExistingToken")
    }

    private suspend fun saveNewToken(token: String, expiration: Long) {
        AppLog.logDebutMethode(this, "saveNewToken")
        AppLog.debug(this, "token : $token expiration : $expiration")
        preferencesRepository.writeString(TOKEN_KEY, token)
        val expiredTime = (System.currentTimeMillis() / 1000) + expiration
        preferencesRepository.writeLong(EXPIRATION_KEY, expiredTime)
        AppLog.logFinMethode(this, "saveNewToken")
    }

    private fun addHeaderOnRequest(chain: Interceptor.Chain): Request {
        AppLog.logDebutMethode(this, "addHeaderOnRequest")

        return if (TOKEN_ENDPOINT == chain.request().url.toUrl().path) {
            AppLog.debug(this, "url contain token")
            val credentials = basic(
                context.getBuildConfigValue("APIM_CLIENT_ID") as String,
                context.getBuildConfigValue("APIM_CLIENT_SECRET") as String
            )
            AppLog.debug(this, "url contain token $credentials")
            chain.request().newBuilder()
                .header(
                    AUTHORIZATION,
                    credentials
                )
                .header(CONTENT_TYPE, MIME)
                .build()
        } else if (!savedToken.isNullOrBlank()) {
            AppLog.debug(
                this, "url don't contain /token and " +
                        "the saved token from preferencesStore is not null"
            )

            AppLog.debug(this, "savedToken : $savedToken")
            AppLog.debug(this, "savedUID : $savedUID")

            chain.request().newBuilder()
                .header(AUTHORIZATION, "Bearer $savedToken")
                .universalHeader()
                .build()
        } else {
            AppLog.debug(
                this, "url don't contain /token and " +
                        "the saved token from preferencesStore is null"
            )

            chain.request()
        }

    }

    private fun Request.Builder.universalHeader() = apply {
        addHeader(UID, savedUID.toString())
        addHeader(VERSION, VERSION_VALUE)
    }

    private fun Interceptor.Chain.proceedToLoggingOnError(
        request: Request,
        chain: Interceptor.Chain
    ): Response {
        AppLog.logDebutMethode(
            this, "proceedToLoggingOnError : " +
                    "$request"
        )

        var response = proceed(request)
        AppLog.debug(this, "response value : $response")
        when (response.code) {
            401 -> {
                AppLog.debug(this, "response code 401")
                runBlocking {
                    saveNewToken("", 0)
                    val tokenResponse = getNewToken()
                    response = if (tokenResponse.isSuccessful && tokenResponse.body != null) {
                        val myRequest = addHeaderOnRequest(chain)
                        proceed(myRequest)
                    } else {
                        tokenResponse
                    }
                }
            }
        }
        return response
    }

    private fun isApimActive(): Boolean {
        return true
    }
}