package com.example.blackburger.usecase

import android.content.Context
import com.example.blackburger.log.AppLog
import com.example.blackburger.network.test.NetworkType
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

sealed class EndPoint(val queryMap: Map<String, String>? = null)

object Sites : EndPoint()

class SendDisurn(val disurnId: String) : EndPoint()

class Manifest(queryMap: Map<String, String>, val isFromPm: Boolean) : EndPoint(queryMap)

class FullEnabled(queryMap: Map<String, String>) : EndPoint(queryMap)

object SendStatut : EndPoint()

class BddSync(val pathEnd: String, queryMap: Map<String, String>, val isFromPm: Boolean) :
    EndPoint(queryMap)

object Token : EndPoint()

class UrlPathTranslatorUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {

    operator fun invoke(endPoint: EndPoint): String {
        AppLog.logDebutMethode(this, "${this::class.qualifiedName}.invoke()")

        return ("/${apimEndPoint(endPoint)}").also {
            AppLog.logFinMethode(this, "${this::class.qualifiedName}.invoke() return $it")
        }
    }

    private fun commonEndPoint(endPoint: EndPoint): String = when (endPoint) {
        Token -> "token"
        else -> {
            throw NotImplementedError()
        }
    }

    private fun centralEndPoint(endPoint: EndPoint): String = when (endPoint) {
        is SendDisurn -> "evenement/$SEND_DISURN_COMMON_END_POINT/${endPoint.disurnId}"
        Sites -> "referentiel/$SITES_COMMON_END_POINT"
        is Manifest -> "referentiel/$MANIFEST_COMMON_END_POINT"
        is FullEnabled -> "referentiel/$FULL_ENABLED_COMMON_END_POINT"
        SendStatut -> "monitoring/$SEND_STATUT_COMMON_END_POINT"
        is BddSync -> "referentiel/$MANIFEST_COMMON_END_POINT/${endPoint.pathEnd}"
        else -> commonEndPoint(endPoint)
    }

    private fun apimEndPoint(endPoint: EndPoint): String = when (endPoint) {
        is SendDisurn -> "SurfEvent/$SEND_DISURN_COMMON_END_POINT/${endPoint.disurnId}"
        Sites -> "SurfRoundDatas/$SITES_COMMON_END_POINT"
        is Manifest -> "SurfRoundDatas/$MANIFEST_COMMON_END_POINT"
        is FullEnabled -> "SurfRoundDatas/$FULL_ENABLED_COMMON_END_POINT"
        SendStatut -> "SurfMonitoring/$SEND_STATUT_COMMON_END_POINT"
        is BddSync -> "SurfRoundDatas/$MANIFEST_COMMON_END_POINT/${endPoint.pathEnd}"
        else -> commonEndPoint(endPoint)
    }

    companion object {
        private const val SEND_DISURN_COMMON_END_POINT = "v2/disurns"
        private const val SITES_COMMON_END_POINT = "v1/sites_eligibles"
        private const val MANIFEST_COMMON_END_POINT = "v1/base"
        private const val FULL_ENABLED_COMMON_END_POINT = "v1/base/is_full_enabled"
        private const val SEND_STATUT_COMMON_END_POINT = "v1/statuts"
    }
}