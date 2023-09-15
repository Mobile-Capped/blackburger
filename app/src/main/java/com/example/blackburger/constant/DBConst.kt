package com.example.blackburger.constant

const val ANNONCES_DB_BASE_NAME_WITHOUT_EXT = "annonces"
const val TERRITOIRE_DB_BASE_NAME_WITHOUT_EXT = "territoire"
const val REFERENTIEL_DB_BASE_NAME_WITHOUT_EXT = "referentiel"
const val TRAVAIL_DB_BASE_NAME_WITHOUT_EXT = "travail"
const val DATA_APP_TRAVAIL_DB_BASE_NAME_WITHOUT_EXT = "data_app_travail"
const val TRAVAIL_DB_BASE_NAME = "$TRAVAIL_DB_BASE_NAME_WITHOUT_EXT.db"
const val DATA_APP_TRAVAIL_DB_BASE_NAME = "$DATA_APP_TRAVAIL_DB_BASE_NAME_WITHOUT_EXT.db"
const val APP_DB_FOLDER_NAME = "appDB"
const val ROOM_DB_FOLDER_NAME = "databases"
const val LOGS_FOLDER_NAME = "Logs"
const val APP_DATA_EXPORT_LOGS_FOLDER_NAME = "DataAppLogs"
const val AIDE_AU_TRI_EXPORT_LOGS_FOLDER_NAME = "AideAuTriLogs"
const val DATASTORE_FOLDER_NAME = "datastore"

const val FIRST_TIME_DOWNLOADED_DB_FOLDER_NAME = "firstTimeDownloadedDatabases"
const val FINAL_FIRST_TIME_DOWNLOADED_DB_FOLDER_NAME = "finalFirstTimeDownloadedDatabases"
const val MIGRATED_DB_FOLDER_NAME = "migratedDatabases"
const val DOWNLOADED_DB_FOLDER_NAME = "downloadedDatabases"
const val FINAL_DOWNLOADED_DB_FOLDER_NAME = "finalDownloadedDatabases"
const val ANNONCES_DB_ASSET_PATH = "databases/01.annonces.782490.db"
const val TERRITOIRE_DB_ASSET_PATH = "databases/02.territoire.782490.db"
const val REFERENTIEL_DB_ASSET_PATH = "databases/03.referentiel.782490.db"
const val TRAVAIL_DB_ASSET_PATH = "databases/04.travail.db"

/**
* La forme que doit avoir un fichier gérant des annonces.
*/
const val  ANNONCE_REGEX = "%s.annonces.db"
/**
 * La forme que doit avoir un fichier gérant des annonces.
 */
const val TERRITOIRE_REGEX = "%s.territoire.db"

/**
 * La forme que doit avoir un fichier gérant des annonces.
 */
const val REFERENTIEL_REGEX = "%s.referentiel.db"

/**
 * La forme que doit avoir un fichier gérant la base de travail.
 */
const val TRAVAIL_REGEX = "travail.db"

/**
 * La forme que doit avoir un fichier stockant les données cryptées, par ex. les clés de décryptage des bases.
 */
const val SECURE_PREFS = "securePrefs.properties"

/**
 * La forme que doit avoir un fichier stockant les données cryptées, par ex. les clés de décryptage des bases.
 */
const val SETTING_PREFS = "settings.preferences_pb"
const val SETTING_PROPERTIES = "settings.properties"