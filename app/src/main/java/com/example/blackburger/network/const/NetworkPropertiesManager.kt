package com.example.blackburger.network.const

import android.content.Context
import com.example.blackburger.R
import java.io.IOException
import java.util.*

/** Message d'erreur provenant du back end s'il y a un 404 lors de la récupération des bases.  */
const val REGATE_NOT_FOUND_ERROR = "Aucune base pour le codeRegate = "

const val NONE = "NONE"
const val FULL = "FULL"
const val DIFF = "DIFF"

const val AUTHORIZATION = "Authorization"
const val UID = "UID"
const val VERSION = "VERSION"
//TODO check if we use this version
const val VERSION_VALUE = "SURFNG-V2.6"

const val MIME = "application/x-www-form-urlencoded"
const val CONTENT_TYPE = "Content-Type"
const val CHARSET_HEADER = "charset"
const val SOURCE_HEADER = "Source"

/**
 * endpoints urls
 */
const val TOKEN_ENDPOINT = "/token"

/**
 * endpoints params
 */
const val DATABASE_TYPE = "type"
const val CODE_REGATE_SITE = "code_regate"
const val LAST_UPDATE_DATE = "last_update_date"
const val APP_VERSION = "version_surfng"
const val DATABASE_SCHEMA_VERSION = "version_schema"
const val DATABASE_VERSION = "vCible"

object NetworkPropertiesManager {

    // APIM new Config
    const val REST_SERVICE_BASESERVICE_BASEURL_APIM = "rest.service.baseservice.baseurl.apim"
    const val REST_SERVICE_REFRESH_TOKEN_BASE_URL = "rest.service.refresh.token.baseurl.apim"

    const val WIFI_SSID_ACP = "wifi.ssid.acp"

    // Propriétés liées aux services REST
    const val REST_SERVICE_UPDATEDATAS_BASEURL = "rest.service.updateddatas.baseurl"
    const val REST_SERVICE_PLAGECAB_BASEURL = "rest.service.plagecab.baseurl"
    const val REST_SERVICE_STOCKSPRIORITAIRES_BASEURL = "rest.service.stocksprioritaires.baseurl"
    const val REST_SERVICE_PARCELCHECK_BASEURL = "rest.service.parcelcheck.baseurl"
    const val REST_SERVICE_BASESERVICE_BASEURL = "rest.service.baseservice.baseurl"
    const val MONITORING_TRANSFER_BASEURL = "rest.service.monitoringtransfer.baseurl"
    const val CONFIG_KEY_EVENTSTRANSFER_BASEURL = "rest.service.eventstransfer.baseurl"
    const val REST_SERVICE_NSCM_PORT = "rest.service.nscm.port"
    const val REST_SERVICE_SITESELIGIBLES_BASEURL = "rest.service.siteseligibles.baseurl"
    const val REST_SERVICE_PREDICT_BASEURL = "rest.service.predict.baseurl"
    const val REST_SERVICE_BASESERVICE_TIMEOUT = "rest.service.baseservice.timeout"
    const val REST_SERVICE_PLAGECAB_READ_TIMEOUT = "rest.service.plagecab.read.timeout"
    const val REST_SERVICE_PLAGECAB_CONNECT_TIMEOUT = "rest.service.plagecab.connect.timeout"
    const val REST_SERVICE_STOCKPRIORITAIRE_READ_TIMEOUT =
        "rest.service.stockprioritaire.read.timeout"
    const val REST_SERVICE_STOCKPRIORITAIRE_CONNECT_TIMEOUT =
        "rest.service.stockprioritaire.connect.timeout"
    const val REST_SERVICE_PARCELCHECK_READ_TIMEOUT = "rest.service.parcelcheck.read.timeout"
    const val REST_SERVICE_PARCELCHECK_CONNECT_TIMEOUT = "rest.service.parcelcheck.connect.timeout"
    const val REST_SERVICE_SITESELIGIBLES_READ_TIMEOUT = "rest.service.siteseligibles.read.timeout"
    const val REST_SERVICE_SITESELIGIBLES_CONNECT_TIMEOUT =
        "rest.service.siteseligibles.connect.timeout"
    const val REST_SERVICE_UPDATEDATA_READ_TIMEOUT = "rest.service.updatedata.read.timeout"
    const val REST_SERVICE_UPDATEDATA_CONNECT_TIMEOUT = "rest.service.updatedata.connect.timeout"
    const val REST_SERVICE_PTV_READ_TIMEOUT = "rest.service.ptv.read.timeout"
    const val REST_SERVICE_PTV_CONNECT_TIMEOUT = "rest.service.ptv.connect.timeout"
    const val REST_SERVICE_PTV_WRITE_TIMEOUT = "rest.service.ptv.write.timeout"


    //SNGF-4593
    const val REST_SERVICE_IH_READ_TIMEOUT = "rest.service.ih.read.timeout"
    const val REST_SERVICE_IH_CONNECT_TIMEOUT = "rest.service.ih.connect.timeout"
    const val REST_SERVICE_IH_WRITE_TIMEOUT = "rest.service.ih.write.timeout"
    const val REST_SERVICE_FULLTERR_JOIN_TIMEOUT = "rest.service.fullterr.join.timeout"

    // SNGF-3757 : création d'une propriété permettant de bypasser ou pas le proxy système pour les services des VIP evts et référentiel
    const val REST_SERVICE_BYPASS_SYSTEM_PROXY = "rest.service.bypass.system.proxy"

    // PATH SERVICE
    const val REST_PATH_PARCELCHECK_BASEURL = "rest.path.parcelcheck.baseurl"
    const val REST_PATH_UPDATEDATAS_BASEURL = "rest.path.updateddatas.baseurl"
    const val REST_PATH_PLAGECAB_BASEURL = "rest.path.plagecab.baseurl"
    const val REST_PATH_STOCKSPRIORITAIRES_BASEURL = "rest.path.stocksprioritaires.baseurl"
    const val REST_PATH_BASESERVICE_BASEURL_DATABASE_SYNC =
        "rest.path.baseservice.baseurl.database.sync"
    const val REST_PATH_BASESERVICE_BASEUR_FULLTERRELIGIBILITY =
        "rest.path.baseservice.baseurl.fulterreligibility"
    const val REST_PATH_BASESERVICE_BASEURL_CLEFSCRYPTAGE =
        "rest.path.baseservice.baseurl.clefscryptage"
    const val REST_PATH_SITESELIGIBLES_BASEURL = "rest.path.siteseligibles.baseurl"
    const val REST_PATH_PREDICT_BASEURL = "rest.path.predict.baseurl"

    // Aide à la saisie
    const val REST_SERVICE_INPUT_HELP_BASEURL = "rest.service.input.help.baseurl"

    private val LOCK = Any()

    @Volatile
    private var mNetworkProperties: Properties? = null

    /**
     * Renvoie les propriétés du fichier network.properties
     *
     * @param ctx Context Android
     * @return propriétés du fichier network.properties
     */
    fun getNetworkProperties(ctx: Context): Properties {
        if (mNetworkProperties == null) {
            synchronized(LOCK) {
                if (mNetworkProperties == null) {
                    mNetworkProperties = Properties()
                    val res = ctx.resources
                    try {
                        mNetworkProperties!!.load(res.openRawResource(R.raw.network))
                    } catch (e: IOException) {
                        throw RuntimeException(e)
                    }
                }
            }
        }
        return mNetworkProperties!!
    }
}






