package com.laposte.surfngfacteo.core_common.constant

const val DATA_APP_PACKAGE_SERVICE = "com.laposte.surfngfacteo.dataapp.service.MessengerService"

/**
 * Command to the service to register a client, receiving callbacks
 * from the service.  The Message's replyTo field must be a Messenger of
 * the client where callbacks should be sent.
 */
const val MSG_REGISTER_CLIENT = 1
const val MSG_CLIENT_REGISTERED = 3

/**
 * requests codes from MessageService
 */
const val GET_BDD = 50
const val FORCE_MAJ = 51
const val GET_DATA_APP_LOGS = 52
const val CHECK_DATAAPP_PERMISSIONS = 53
const val LAUNCH_DESCOVERY = 54

/**
 * responses codes from MessageService
 */

const val BDD_SUCCESS = 100
const val BDD_LOADING = 101
const val BDD_FAILED = 102
const val BDD_FAILED_CLIENT_NOT_AUTHORIZED = 103

const val FORCE_MAJ_SUCCESS = 110
const val FORCE_MAJ_LOADING = 111
const val FORCE_MAJ_FAILED = 112

const val GET_DATA_APP_LOGS_SUCCESS = 120
const val GET_DATA_APP_LOGS_LOADING = 121
const val GET_DATA_APP_LOGS_FAILED = 122
const val DATA_APP_PERMISSION_GRANTED = 123
const val DATA_APP_PERMISSION_DENIED = 124
const val DISCOVERY_END = 125

/**
 * params and results keys
 */
const val CODE_REGATE_FOR_REQUESTED_DB_KEY = "CODE_REGATE_FOR_REQUESTED_DB"
const val CODES_REGATE_SITE_RATTACHEESD_DB_KEY = "CODES_REGATE_SITE_RATTACHEESD_DB_KEY"

const val PACKAGE_REQUESTING_DB_KEY = "PACKAGE_REQUESTING_DB_KEY"

const val MIGRATED_DB_DATA = "MIGRATED_DB_DATA"

const val DATA_APP_LOGS = "DATA_APP_LOGS"

const val IMEI_KEY = "IMEI_KEY"
const val IS_SITE_REINIT = "IS_SITE_REINIT"