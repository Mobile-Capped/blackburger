package com.example.blackburger.network.api

import android.content.Context
import com.example.blackburger.network.resource.ResultStatut
import com.example.blackburger.log.AppLog
import com.example.blackburger.network.const.NetworkPropertiesManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.laposte.surfngfacteo.core_common.exception.UnknownException
import com.example.blackburger.network.const.NetworkPropertiesManager.REST_SERVICE_BASESERVICE_BASEURL_APIM
import com.example.blackburger.network.interceptor.NetworkInterceptor
import com.example.blackburger.repository.PreferencesRepository
import com.example.blackburger.network.model.response.SiteResponse
import com.example.blackburger.network.model.response.TokenApimResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkHttp @Inject constructor(
    @ApplicationContext context: Context,
    networkJson: Json,
    preferencesRepository: PreferencesRepository
    ) : NetworkDataSource {


    private val networkInterceptor = NetworkInterceptor(context, preferencesRepository,this)

    private val httpClientLogs = OkHttpClient.Builder()
        .addInterceptor(networkInterceptor).addInterceptor(HttpLoggingInterceptor {
            AppLog.debug(this,"LogApi : $it")
        }.apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }).build()

    /*
     TODO : Attention, il faudra gérer les messages d'erreur du backend explicitement
      (400,500 etc..) et établir une stratégie de retry si besoin
     */
    private val retrofitNetwork = NetworkPropertiesManager.getNetworkProperties(context).let {
        Retrofit.Builder().baseUrl(
            it.getProperty(REST_SERVICE_BASESERVICE_BASEURL_APIM)
        ).client(
            httpClientLogs
        ).addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    private val networkApi = retrofitNetwork.create(NetworkApi::class.java)


    override suspend fun getToken(): Response<TokenApimResponse> {
        return networkApi.getToken()
    }


    override suspend fun getEligibleSites(): ResultStatut<List<SiteResponse>> {
        val response = networkApi.getEligibleSites()
        return getResponse(response)
    }

    private fun <T> getResponse(networkResponse: Response<T>): ResultStatut<T> {
        return when {
            networkResponse.isSuccessful && networkResponse.body() != null -> {
                ResultStatut.Success(networkResponse.body()!!)
            }
            !networkResponse.isSuccessful && networkResponse.errorBody() != null -> {
                val ex = Exception(networkResponse.errorBody().toString())
                ex.printStackTrace()
                AppLog.debug(this, "Error in getResponse() . Error: ${networkResponse.message()}", ex)
                ResultStatut.Error(ex)
            }
            else -> {
                AppLog.debug(
                    this, "Error in getResponse(). Error: ${networkResponse.message()} -> ${
                        networkResponse.errorBody()?.string()
                    }"
                )
                ResultStatut.Error(UnknownException())
            }
        }
    }

}