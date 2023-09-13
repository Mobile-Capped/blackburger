package com.example.blackburger.log

import android.app.Application
import android.os.Build
import android.os.Environment
import android.os.Process
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Classe utilitaire permettant de clarifier la gestion des logs au sein du code.
 */
object AppLog {

    /**
     * Propriété logback
     */
    const val LOGBACK_FILENAME_KEY = "filename-log"

    /**
     * Détermine s'il faut désactiver complètement le logging, ou utiliser la configuration logback.
     */
    private var inactiveForPerf = false

    /**
     * Méthode utilitaire permettant d'obtenir le logger.
     *
     * @param clazz : La classe appelante.
     * @return Le [Logger] de la classe appelante.
     */
    private fun getLogger(clazz: Any): Logger {
        return LoggerFactory.getLogger(clazz.javaClass.simpleName)
    }

    private fun getLogger(clazz: String): Logger {
        return LoggerFactory.getLogger(clazz)
    }

    /**
     * Vérifie si le logger gère la classe au niveau TRACE.
     *
     * @param clazz : La classe dont on va chercher le logger.
     */
    private fun isTraceEnabled(clazz: Any): Boolean {
        return getLogger(clazz).isTraceEnabled
    }

    /**
     * Vérifie si le logger gère la classe au niveau DEBUG.
     *
     * @param clazz : La classe dont on va chercher le logger.
     */
    private fun isDebugEnabled(clazz: Any): Boolean {
        return getLogger(clazz).isDebugEnabled
    }

    /**
     * Vérifie si le logger gère la classe au niveau INFO.
     *
     * @param clazz : La classe dont on va chercher le logger.
     */
    private fun isInfoEnabled(clazz: Any): Boolean {
        return getLogger(clazz).isInfoEnabled
    }

    /**
     * Vérifie si le logger gère la classe au niveau WARN.
     *
     * @param clazz : La classe dont on va chercher le logger.
     */
    private fun isWarnEnabled(clazz: Any): Boolean {
        return getLogger(clazz).isWarnEnabled
    }

    /**
     * Vérifie si le logger gère la classe au niveau ERROR.
     *
     * @param clazz : La classe dont on va chercher le logger.
     */
    private fun isErrorEnabled(clazz: Any): Boolean {
        return getLogger(clazz).isErrorEnabled
    }

    /**
     * Permet d'activer ou de désactivement entièrement la gestion de logs.
     *
     * @param isInactiveForPerf : true pour désactiver les logs, false pour utiliser la configuration logback.
     */
    fun setInactiveForPerf(isInactiveForPerf: Boolean) {
        inactiveForPerf = isInactiveForPerf
    }

    /**
     * A insérer au début de chaque méthode.
     *
     * @param clazz   : La classe appelante.
     * @param methode : Le nom de la méthode appelante.
     */
    fun logDebutMethode(clazz: Any, methode: String) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug(clazz.javaClass.simpleName + "." + methode + " - DEBUT")
        }
    }

    fun logDebutMethode(clazz: String, methode: String) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug(clazz.javaClass.simpleName + "." + methode + " - DEBUT")
        }
    }

    /**
     * A insérer à la fin de chaque méthode.
     *
     * @param clazz   : La classe appelante.
     * @param methode : Le nom de la méthode appelante.
     */
    fun logFinMethode(clazz: Any, methode: String) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug(clazz.javaClass.simpleName + "." + methode + " - FIN")
        }
    }

    fun logFinMethode(clazz: String, methode: String) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug(clazz.javaClass.simpleName + "." + methode + " - FIN")
        }
    }

    /**
     * Permet d'indiquer la requête SQL que le code va exécuter.
     *
     * @param clazz : La classe appelante.
     * @param sql   : La requête SQL qui va être executée.
     */
    fun logSql(clazz: Any, sql: String) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug("Log SQL : $sql")
        }
    }

    fun logSql(clazz: String, sql: String) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug("Log SQL : $sql")
        }
    }

    /**
     * Vérifie si le logger peut logger une trace, et fait appel à [Logger.trace].
     *
     * @param clazz : La classe appelante.
     * @param msg   : Le message à logger.
     */
    fun trace(clazz: Any, msg: String?) {
        if (!inactiveForPerf && isTraceEnabled(clazz)) {
            getLogger(clazz).trace(msg)
        }
    }

    fun trace(clazz: String, msg: String?) {
        if (!inactiveForPerf && isTraceEnabled(clazz)) {
            getLogger(clazz).trace(msg)
        }
    }

    /**
     * Vérifie si le logger peut logger une trace, et fait appel à [Logger.trace].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param arg    : L'objet à injecter dans 'format'.
     */
    fun trace(clazz: Any, format: String?, arg: Any?) {
        if (!inactiveForPerf && isTraceEnabled(clazz)) {
            getLogger(clazz).trace(format, arg)
        }
    }

    fun trace(clazz: String, format: String?, arg: Any?) {
        if (!inactiveForPerf && isTraceEnabled(clazz)) {
            getLogger(clazz).trace(format, arg)
        }
    }

    /**
     * Vérifie si le logger peut logger une trace, et fait appel à [Logger.trace].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param args   : Un ensemble d'objets à injecter dans 'format'.
     */
    fun trace(clazz: Any, format: String?, vararg args: Any?) {
        if (!inactiveForPerf && isTraceEnabled(clazz)) {
            getLogger(clazz).trace(format, *args)
        }
    }

    fun trace(clazz: String, format: String?, vararg args: Any?) {
        if (!inactiveForPerf && isTraceEnabled(clazz)) {
            getLogger(clazz).trace(format, *args)
        }
    }

    /**
     * Vérifie si le logger peut logger une trace, et fait appel à [Logger.trace].
     *
     * @param clazz : La classe appelante.
     * @param msg   : Le message précédant la stacktrace de l'exception.
     * @param t     : L'exception dont la stacktrace va être affichée.
     */
    fun trace(clazz: Any, msg: String?, t: Throwable?) {
        if (!inactiveForPerf && isTraceEnabled(clazz)) {
            try {
                getLogger(clazz).trace(msg, t)
            } catch (e: Exception) {
                warn(
                    AppLog,
                    "trace() : Something wrong happened while handling Throwable (stack trace must be null...?)"
                )
            }
        }
    }

    fun trace(clazz: String, msg: String?, t: Throwable?) {
        if (!inactiveForPerf && isTraceEnabled(clazz)) {
            try {
                getLogger(clazz).trace(msg, t)
            } catch (e: Exception) {
                warn(
                    AppLog,
                    "trace() : Something wrong happened while handling Throwable (stack trace must be null...?)"
                )
            }
        }
    }

    /**
     * Vérifie si le logger peut logger une trace, et fait appel à [Logger.trace].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param arg1   : Le premier objet à injecter dans 'format'.
     * @param arg2   : Le second objet à injecter dans 'format'.
     */
    fun trace(clazz: Any, format: String?, arg1: Any?, arg2: Any?) {
        if (!inactiveForPerf && isTraceEnabled(clazz)) {
            getLogger(clazz).trace(format, arg1, arg2)
        }
    }

    fun trace(clazz: String, format: String?, arg1: Any?, arg2: Any?) {
        if (!inactiveForPerf && isTraceEnabled(clazz)) {
            getLogger(clazz).trace(format, arg1, arg2)
        }
    }

    /**
     * Vérifie si le logger peut logger un debug, et fait appel à [Logger.debug].
     *
     * @param clazz : La classe appelante.
     * @param msg   : Le message à logger.
     */
    fun debug(clazz: Any, msg: String?) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug(msg)
        }
    }

    fun debug(clazz: String, msg: String?) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug(msg)
        }
    }

    /**
     * Vérifie si le logger peut logger un debug, et fait appel à [Logger.debug].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param arg    : L'objet à injecter dans 'format'.
     */
    fun debug(clazz: Any, format: String?, arg: Any?) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug(format, arg)
        }
    }

    fun debug(clazz: String, format: String?, arg: Any?) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug(format, arg)
        }
    }

    /**
     * Vérifie si le logger peut logger un debug, et fait appel à [Logger.debug].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param args   : Un ensemble d'objets à injecter dans 'format'.
     */
    fun debug(clazz: Any, format: String?, vararg args: Any?) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug(format, *args)
        }
    }

    fun debug(clazz: String, format: String?, vararg args: Any?) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug(format, *args)
        }
    }

    /**
     * Vérifie si le logger peut logger un debug, et fait appel à [Logger.debug].
     *
     * @param clazz : La classe appelante.
     * @param msg   : Le message précédant la stacktrace de l'exception.
     * @param t     : L'exception dont la stacktrace va être affichée.
     */
    fun debug(clazz: Any, msg: String?, t: Throwable?) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            try {
                getLogger(clazz).debug(msg, t)
            } catch (e: Exception) {
                warn(
                    AppLog,
                    "debug() : Something wrong happened while handling Throwable (stack trace must be null...?)"
                )
            }
        }
    }

    fun debug(clazz: String, msg: String?, t: Throwable?) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            try {
                getLogger(clazz).debug(msg, t)
            } catch (e: Exception) {
                warn(
                    AppLog,
                    "debug() : Something wrong happened while handling Throwable (stack trace must be null...?)"
                )
            }
        }
    }

    /**
     * Vérifie si le logger peut logger un debug, et fait appel à [Logger.debug].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param arg1   : Le premier objet à injecter dans 'format'.
     * @param arg2   : Le second objet à injecter dans 'format'.
     */
    fun debug(clazz: Any, format: String?, arg1: Any?, arg2: Any?) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug(format, arg1, arg2)
        }
    }

    fun debug(clazz: String, format: String?, arg1: Any?, arg2: Any?) {
        if (!inactiveForPerf && isDebugEnabled(clazz)) {
            getLogger(clazz).debug(format, arg1, arg2)
        }
    }

    /**
     * Vérifie si le logger peut logger une info, et fait appel à [Logger.info].
     *
     * @param clazz : La classe appelante.
     * @param msg   : Le message à logger.
     */
    fun info(clazz: Any, msg: String?) {
        if (!inactiveForPerf && isInfoEnabled(clazz)) {
            getLogger(clazz).info(msg)
        }
    }

    fun info(clazz: String, msg: String?) {
        if (!inactiveForPerf && isInfoEnabled(clazz)) {
            getLogger(clazz).info(msg)
        }
    }

    /**
     * Vérifie si le logger peut logger une info, et fait appel à [Logger.info].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param arg    : L'objet à injecter dans 'format'.
     */
    fun info(clazz: Any, format: String?, arg: Any?) {
        if (!inactiveForPerf && isInfoEnabled(clazz)) {
            getLogger(clazz).info(format, arg)
        }
    }

    fun info(clazz: String, format: String?, arg: Any?) {
        if (!inactiveForPerf && isInfoEnabled(clazz)) {
            getLogger(clazz).info(format, arg)
        }
    }

    /**
     * Vérifie si le logger peut logger une info, et fait appel à [Logger.info].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param args   : Un ensemble d'objets à injecter dans 'format'.
     */
    fun info(clazz: Any, format: String?, vararg args: Any?) {
        if (!inactiveForPerf && isInfoEnabled(clazz)) {
            getLogger(clazz).info(format, *args)
        }
    }

    fun info(clazz: String, format: String?, vararg args: Any?) {
        if (!inactiveForPerf && isInfoEnabled(clazz)) {
            getLogger(clazz).info(format, *args)
        }
    }

    /**
     * Vérifie si le logger peut logger une info, et fait appel à [Logger.info].
     *
     * @param clazz : La classe appelante.
     * @param msg   : Le message précédant la stacktrace de l'exception.
     * @param t     : L'exception dont la stacktrace va être affichée.
     */
    fun info(clazz: Any, msg: String?, t: Throwable?) {
        if (!inactiveForPerf && isInfoEnabled(clazz)) {
            try {
                getLogger(clazz).info(msg, t)
            } catch (e: Exception) {
                warn(
                    AppLog,
                    "info() : Something wrong happened while handling Throwable (stack trace must be null...?)"
                )
            }
        }
    }

    fun info(clazz: String, msg: String?, t: Throwable?) {
        if (!inactiveForPerf && isInfoEnabled(clazz)) {
            try {
                getLogger(clazz).info(msg, t)
            } catch (e: Exception) {
                warn(
                    AppLog,
                    "info() : Something wrong happened while handling Throwable (stack trace must be null...?)"
                )
            }
        }
    }

    /**
     * Vérifie si le logger peut logger une info, et fait appel à [Logger.info].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param arg1   : Le premier objet à injecter dans 'format'.
     * @param arg2   : Le second objet à injecter dans 'format'.
     */
    fun info(clazz: Any, format: String?, arg1: Any?, arg2: Any?) {
        if (!inactiveForPerf && isInfoEnabled(clazz)) {
            getLogger(clazz).info(format, arg1, arg2)
        }
    }

    fun info(clazz: String, format: String?, arg1: Any?, arg2: Any?) {
        if (!inactiveForPerf && isInfoEnabled(clazz)) {
            getLogger(clazz).info(format, arg1, arg2)
        }
    }

    /**
     * Vérifie si le logger peut logger un warn, et fait appel à [Logger.warn].
     *
     * @param clazz : La classe appelante.
     * @param msg   : Le message à logger.
     */
    fun warn(clazz: Any, msg: String?) {
        if (!inactiveForPerf && isWarnEnabled(clazz)) {
            getLogger(clazz).warn(msg)
        }
    }

    fun warn(clazz: String, msg: String?) {
        if (!inactiveForPerf && isWarnEnabled(clazz)) {
            getLogger(clazz).warn(msg)
        }
    }

    /**
     * Vérifie si le logger peut logger un warn, et fait appel à [Logger.warn].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param arg    : L'objet à injecter dans 'format'.
     */
    fun warn(clazz: Any, format: String?, arg: Any?) {
        if (!inactiveForPerf && isWarnEnabled(clazz)) {
            getLogger(clazz).warn(format, arg)
        }
    }

    fun warn(clazz: String, format: String?, arg: Any?) {
        if (!inactiveForPerf && isWarnEnabled(clazz)) {
            getLogger(clazz).warn(format, arg)
        }
    }

    /**
     * Vérifie si le logger peut logger un warn, et fait appel à [Logger.warn].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param args   : Un ensemble d'objets à injecter dans 'format'.
     */
    fun warn(clazz: Any, format: String?, vararg args: Any?) {
        if (!inactiveForPerf && isWarnEnabled(clazz)) {
            getLogger(clazz).warn(format, *args)
        }
    }

    fun warn(clazz: String, format: String?, vararg args: Any?) {
        if (!inactiveForPerf && isWarnEnabled(clazz)) {
            getLogger(clazz).warn(format, *args)
        }
    }

    /**
     * Vérifie si le logger peut logger un warn, et fait appel à [Logger.warn].
     *
     * @param clazz : La classe appelante.
     * @param msg   : Le message précédant la stacktrace de l'exception.
     * @param t     : L'exception dont la stacktrace va être affichée.
     */
    fun warn(clazz: Any, msg: String?, t: Throwable?) {
        if (!inactiveForPerf && isWarnEnabled(clazz)) {
            try {
                getLogger(clazz).warn(msg, t)
            } catch (e: Exception) {
                warn(
                    AppLog,
                    "warn() : Something wrong happened while handling Throwable (stack trace must be null...?)"
                )
            }
        }
    }

    fun warn(clazz: String, msg: String?, t: Throwable?) {
        if (!inactiveForPerf && isWarnEnabled(clazz)) {
            try {
                getLogger(clazz).warn(msg, t)
            } catch (e: Exception) {
                warn(
                    AppLog,
                    "warn() : Something wrong happened while handling Throwable (stack trace must be null...?)"
                )
            }
        }
    }

    /**
     * Vérifie si le logger peut logger un warn, et fait appel à [Logger.warn].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param arg1   : Le premier objet à injecter dans 'format'.
     * @param arg2   : Le second objet à injecter dans 'format'.
     */
    fun warn(clazz: Any, format: String?, arg1: Any?, arg2: Any?) {
        if (!inactiveForPerf && isWarnEnabled(clazz)) {
            getLogger(clazz).warn(format, arg1, arg2)
        }
    }

    fun warn(clazz: String, format: String?, arg1: Any?, arg2: Any?) {
        if (!inactiveForPerf && isWarnEnabled(clazz)) {
            getLogger(clazz).warn(format, arg1, arg2)
        }
    }

    /**
     * Vérifie si le logger peut logger une erreur, et fait appel à [Logger.error].
     *
     * @param clazz : La classe appelante.
     * @param msg   : Le message à logger.
     */
    fun error(clazz: Any, msg: String?) {
        if (!inactiveForPerf && isErrorEnabled(clazz)) {
            getLogger(clazz).error(msg)
        }
    }

    fun error(clazz: String, msg: String?) {
        if (!inactiveForPerf && isErrorEnabled(clazz)) {
            getLogger(clazz).error(msg)
        }
    }

    /**
     * Vérifie si le logger peut logger une erreur, et fait appel à [Logger.error].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param arg    : L'objet à injecter dans 'format'.
     */
    fun error(clazz: Any, format: String?, arg: Any?) {
        if (!inactiveForPerf && isErrorEnabled(clazz)) {
            getLogger(clazz).error(format, arg)
        }
    }

    fun error(clazz: String, format: String?, arg: Any?) {
        if (!inactiveForPerf && isErrorEnabled(clazz)) {
            getLogger(clazz).error(format, arg)
        }
    }

    /**
     * Vérifie si le logger peut logger une erreur, et fait appel à [Logger.error].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param args   : Un ensemble d'objets à injecter dans 'format'.
     */
    fun error(clazz: Any, format: String?, vararg args: Any?) {
        if (!inactiveForPerf && isErrorEnabled(clazz)) {
            getLogger(clazz).error(format, *args)
        }
    }

    fun error(clazz: String, format: String?, vararg args: Any?) {
        if (!inactiveForPerf && isErrorEnabled(clazz)) {
            getLogger(clazz).error(format, *args)
        }
    }

    /**
     * Vérifie si le logger peut logger une erreur, et fait appel à [Logger.error].
     *
     * @param clazz : La classe appelante.
     * @param msg   : Le message précédant la stacktrace de l'exception.
     * @param t     : L'exception dont la stacktrace va être affichée.
     */
    fun error(clazz: Any, msg: String?, t: Throwable?) {
        if (!inactiveForPerf && isErrorEnabled(clazz)) {
            try {
                getLogger(clazz).error(msg, t)
            } catch (e: Exception) {
                warn(
                    AppLog,
                    "error() : Something wrong happened while handling Throwable (stack trace must be null...?)"
                )
            }
        }
    }

    fun error(clazz: String, msg: String?, t: Throwable?) {
        if (!inactiveForPerf && isErrorEnabled(clazz)) {
            try {
                getLogger(clazz).error(msg, t)
            } catch (e: Exception) {
                warn(
                    AppLog,
                    "error() : Something wrong happened while handling Throwable (stack trace must be null...?)"
                )
            }
        }
    }

    /**
     * Vérifie si le logger peut logger une erreur, et fait appel à [Logger.error].
     *
     * @param clazz  : La classe appelante.
     * @param format : Le formatteur à utiliser.
     * @param arg1   : Le premier objet à injecter dans 'format'.
     * @param arg2   : Le second objet à injecter dans 'format'.
     */
    fun error(clazz: Any, format: String?, arg1: Any?, arg2: Any?) {
        if (!inactiveForPerf && isErrorEnabled(clazz)) {
            getLogger(clazz).error(format, arg1, arg2)
        }
    }

    fun error(clazz: String, format: String?, arg1: Any?, arg2: Any?) {
        if (!inactiveForPerf && isErrorEnabled(clazz)) {
            getLogger(clazz).error(format, arg1, arg2)
        }
    }

    fun logApplicationInformations(app: Application, appVersion: String) {

        // Informations concernant le device et l'apk
        debug(app, "************** APPLICATION START **************")
        debug(app, "** PHONE BRAND : " + Build.BRAND)
        debug(app, "** DEVICE : " + Build.DEVICE)
        debug(app, "** MODEL : " + Build.MODEL)
        debug(app, "** APP_VERSION : $appVersion")
        debug(app, "** OS VERSION : " + Build.VERSION.RELEASE)
        debug(app, "** SD CARD : " + Environment.getExternalStorageState())

        // Informations runtime applicatif
        debug(app, "************** RUNTIME LOG **************")
        debug(app, "** INSTANCE OF THIS : $app")
        debug(app, "** PID : " + Process.myPid())
        debug(app, "*************************************************")
    }

    fun logNetworkInformations(app: Application?) {
        // Informations réseaux
        /*
        IConnectionManager device = app.getAndroidDevice().getConnectionManager();
        debug(app, "************** NETWORK LOG **************");
        debug(app, "** IS_CELLULAR_NETWORK_CONNECTED : " + device.isMobileConnected());
        debug(app, "** IS_WIFI_CONNECTED : " + device.isWifiConnected());
        debug(app, "** WIFI_SSID : " + device.getConnectedSSID());
        debug(app, "** WIFI_IP_ADDRESS : " + device.getIpAddress(device.isWifiConnected() ? "wlan" : "rmnet", true));
         */
    }
}