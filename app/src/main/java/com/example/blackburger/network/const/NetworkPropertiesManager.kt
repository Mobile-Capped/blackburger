package com.example.blackburger.network.const

import java.util.Properties

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

    private val LOCK = Any()

    @Volatile
    private var mNetworkProperties: Properties? = null

}






