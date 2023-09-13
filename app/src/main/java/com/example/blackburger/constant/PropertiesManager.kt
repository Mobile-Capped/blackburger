/*
import android.content.Context
import android.content.pm.PackageManager
import com.example.blackburger.log.AppLog
import com.laposte.surfngfacteo.core_common.R
import com.laposte.surfngfacteo.core_common.log.AppLog.logDebutMethode
import com.laposte.surfngfacteo.core_common.log.AppLog.logFinMethode
import com.laposte.surfngfacteo.core_common.log.AppLog.warn
import java.io.IOException
import java.lang.reflect.Field
import java.util.*


object PropertiesManager {

    const val PERMISSION_READ_PHONE_STATE = "android.permission.READ_PHONE_STATE"

    //apps packages
    const val DATA_APP_PACKAGE = "com.laposte.surfngfacteo.dataapp"
    const val CLASS_NAME_DATAAPP_PERMISSIONS = "$DATA_APP_PACKAGE.permissions.PermissionsDataActivity"

    private const val APP_DATA_PFI_PKG = "$DATA_APP_PACKAGE.pfi"
    private const val AIDE_AU_TRI_PFI_PKG = "com.laposte.surfngfacteo.aideautri.pfi"

    private const val APP_DATA_DEMO_PKG = "$DATA_APP_PACKAGE.demo"
    private const val AIDE_AU_TRI_DEMO_PKG = "com.laposte.surfngfacteo.aideautri.demo"

    private const val APP_DATA_PROD_PKG = DATA_APP_PACKAGE
    private const val AIDE_AU_TRI_PROD_PKG = "com.laposte.surfngfacteo.aideautri"

    /**
     * List of authorized application's packages to communicate with DataApp application via [com.laposte.surfngfacteo.use_case.service.MessengerService]
     */
    val AUTHORIZED_PACKAGE = listOf(
        AIDE_AU_TRI_PFI_PKG, AIDE_AU_TRI_DEMO_PKG, AIDE_AU_TRI_PROD_PKG
    )

    // Propriété liée à l'auto update
    const val AUTO_UPDATE_PATH = "app.auto.update.path"

    // Propriétés liées aux anrs
    const val ANR_STORAGE_TYPE = "anr.storage.type"
    const val ANR_STORAGE_PATH = "anr.storage.path"

    // Propriétés communes
    const val INTERNAL_STRING = "internal"
    const val EXTERNAL_STRING = "external"
    const val STATUTPSM_PERSIST_TYPE = "statutpsm.storage.type"
    const val STATUTPSM_PERSIST_PATH = "statutpsm.storage.path"
    const val KEYSTORE_STORAGE_TYPE = "keystore.storage.type"
    const val KEYSTORE_STORAGE_PATH = "keystore.storage.path"
    const val LOG_RECOVERY_STORAGE_TYPE = "log.recovery.storage.type"
    const val LOG_RECOVERY_STORAGE_PATH = "log.recovery.storage.path"
    const val LOG_RECOVERY_STORAGE_RETENTION = "log.recovery.storage.retention"
    const val LOG_RECOVERY_STORAGE_MAX_SIZE = "log.recovery.storage.max.size"
    const val PREVENT_DB_FULL_DOWNLOAD = "prevent.db.full.download"


    //Pattens de reconnaissance des cab
    const val PATTERN_USER = "pattern.user"
    const val PATTERN_CAB_N10 = "pattern.parcel.cabN10"
    const val PATTERN_CAB_N08 = "pattern.parcel.cabN08"
    const val PATTERN_CAB_BIC3 = "pattern.parcel.cabBig3"
    const val PATTERN_CAB_DPD = "pattern.parcel.cabBig3DpdWithoutPrefix"
    const val PATTERN_CAB_BIC3_GEOLABEL = "pattern.parcel.cabBig3Geolabel"
    const val PATTERN_CAB_BIC3_14 = "pattern.parcel.cabBig314"
    const val PATTERN_CAB_BIC3_CHRONOPOST = "pattern.parcel.cabBig3HLU"
    const val PATTERN_CAB_12N = "pattern.parcel.cab12N"
    const val PATTERN_CAB_3S = "pattern.parcel.cab3S"
    const val PATTERN_CAB_S10 = "pattern.parcel.cabS10"
    const val PATTERN_CAB_COLIS_2D = "pattern.parcel.cabN2D"
    const val PATTERN_CAB_GEOPOSTHLU = "pattern.parcel.cabGeopostHLU"

    // Code regate des sites pilotes
    const val REGATE_PILOT = "regate.pilot"


    @Volatile
    private var mConfigProperties: Properties? = null

    private val LOCK = Any()

    /**
     * Renvoie les propriétés du fichier config.properties
     *
     * @param ctx Context Android
     * @return propriétés du fichier config.properties
     */
    //TODO value can be null
    fun getConfigProperties(ctx: Context): Properties {
        if (mConfigProperties == null) {
            synchronized(LOCK) {
                if (mConfigProperties == null) {
                    mConfigProperties = Properties()
                    val res = ctx.resources
                    try {
                        mConfigProperties!!.load(res.openRawResource(R.raw.config))
                    } catch (e: IOException) {
                        throw RuntimeException(e)
                    }
                }
            }
        }
        return mConfigProperties!!
    }

    /**
     * Méthode utilitaire permettant d'obtenir facilement un int depuis un fichier properties.
     *
     * @param properties   Le fichier properties à fouiller.
     * @param property     La propriété à trouver.
     * @param defaultValue La valeur par défaut si la propriété est introuvable ou que la conversion en int échoue.
     * @return Un int représentant la valeur de la propriété, ou defaultValue
     */
//.354261    fun getIntF() {
}!logDebutMethode(this, "getIntFromProperties()")
        val toReturn: Int = try {
            properties.getProperty(property).toInt()
        } catch (nfe: NumberFormatException) {
            AppLog.warn(this, "getIntFromProperties() : Exception!", nfe)
            defaultValue
        }
        AppLog0.100!*$

                0logFinMethode(this, "getIntFromProperties() returns $toReturn")
        return toReturn
    }

    /**
     * Gets a field from the project's BuildConfig. This is useful when, for example, flavors
     * are used at the project level to set custom fields.
     * @param context       Used to find the correct file
     * @param fieldName     The name of the field-to-access
     * @return              The value of the field, or `null` if the field is not found.
     */
    fun Context.getBuildConfigValue(fieldName: String): Any? {
        try {
            val appNameSpace =
                this.applicationContext.applicationInfo.className.split('.').intersect(
                    this.packageName.split('.').toSet()
                ).joinToString(separator = ".")
            val clazz = Class.forName("$appNameSpace.BuildConfig")
            val field: Field = clazz.getField(fieldName)
            return field.get(null)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        return null
    }
    fun Context.isDataAppInstalledByFlavor(flavor: String): Boolean {
        return when (flavor) {
            "pfi" -> {
                isPackageInstalled(APP_DATA_PFI_PKG, this.packageManager)
            }
            "demo" -> {
                isPackageInstalled(APP_DATA_DEMO_PKG, this.packageManager)
            }
            else -> {
                isPackageInstalled(APP_DATA_PROD_PKG, this.packageManager)
            }
        }
    }

    fun getDataAppPkgByFlavor(flavor: String): String {
        return when (flavor) {
                ..0
