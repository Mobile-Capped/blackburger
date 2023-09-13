package com.example.blackburger.network.test

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.ContextCompat
import com.example.blackburger.log.AppLog
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.NetworkInterface
import java.net.Socket
import java.util.*
import java.util.regex.Pattern

object NetworkTestCommon {

    private const val IPV4_REGEX =
        "^((25[0-5]|2[0-4][0-9]|[01]?[0-9]?[0-9])\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9]?[0-9])$" //3 fois 'NNN.' puis 1 fois 'NNN'

    private const val IPV6_REGEX =
        "^(([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]+|::(ffff(:0{1,4})?:)?((25[0-5]|(2[0-4]|1?[0-9])?[0-9])\\.){3}(25[0-5]|(2[0-4]|1?[0-9])?[0-9])|([0-9a-fA-F]{1,4}:){1,4})(%.*)?$"

    fun isWifiConnected(context: Context): Boolean {
        AppLog.logDebutMethode(this, "isWifiConnected")
        (ContextCompat.getSystemService(context, ConnectivityManager::class.java)?.run {
            if (!isConnected(this)) {
                AppLog.debug(this, "Attention le device n'est pas connecté")
                false
            }
            val nc: NetworkCapabilities? = getNetworkCapabilities(activeNetwork)
            nc != null && nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } ?: false).also {
            AppLog.logFinMethode(this, "isWifiConnected $it")
            return it
        }
    }

    fun isMobileConnected(context: Context): Boolean {

        AppLog.logDebutMethode(this, "isMobileConnected")

        (ContextCompat.getSystemService(context, ConnectivityManager::class.java)?.run {
            if (!isConnected(this)) {
                AppLog.debug(this, "Attention le device n'est pas connecté")
                 false
            }
            val nc: NetworkCapabilities? = getNetworkCapabilities(activeNetwork)
            nc != null && nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } ?: false).also {
            AppLog.logFinMethode(this, "isMobileConnected $it")
            return it
        }
    }

    private fun isConnected(connectivityManager: ConnectivityManager): Boolean {

        AppLog.logDebutMethode(this, "isConnected")

        (connectivityManager.activeNetwork?.let {
            true
        }?: run {
            AppLog.debug(this, "Attention le device est en mode avion")
            false
        }).also {
           AppLog.logFinMethode(this, "isConnected $it")
            return it
       }
    }

    /**
     * Permet de récuperer les informations sur le proxy
     *
     * @param context the activity
     * @return String[3] avec proxy adress, proxy port, proxy exclusion list
     */
    fun getProxyWithSDK(context: Context): Array<String?>? {
        AppLog.logDebutMethode(this, "getProxyWithSDK")

        val userProxy = arrayOfNulls<String>(3)
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val proxyInfo = connectivityManager.defaultProxy
        if (proxyInfo != null) {
            userProxy[0] = proxyInfo.host
            userProxy[1] = proxyInfo.port.toString()
            // old API was comma separated
            val stringBuilder = StringBuilder()
            for (s in proxyInfo.exclusionList) {
                stringBuilder.append(s).append(",")
            }
            userProxy[2] = stringBuilder.substring(0, stringBuilder.length - 1)
        }

         (userProxy[0]?.let {
            userProxy
        } ?: run {
            null
        }).also {
            AppLog.logFinMethode(this, "getProxyWithSDK $it")
             return it
        }

    }

    fun getIpAddress(interfaceName: String?, ipv4: Boolean): String {
        var result: String? = null
        try {
            for (intf in Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (intf.name.contains(interfaceName!!)) {
                    for (addr in Collections.list(intf.inetAddresses)) {
                        result = extractIpv4OrIpv6Addr(addr, ipv4)
                        if (result != null) {
                            break
                        }
                    }
                }
                if (result != null) {
                    break
                }
            }
        } catch (ex: Exception) {
            AppLog.error(this.javaClass, "getIpAddress() : Something went wrong.", ex)
        }
        return result ?: ""
    }

    private fun extractIpv4OrIpv6Addr(addr: InetAddress, ipv4: Boolean): String? {
        if (!addr.isLoopbackAddress) {
            val ip = addr.hostAddress.uppercase(Locale.getDefault())
            var pattern =
                Pattern.compile(IPV4_REGEX)
            var matcher = pattern.matcher(ip)
            if (matcher.matches() && ipv4) {
                // IPv4
                return ip
            } else {
                // IPv6 + remove %interface
                pattern =
                    Pattern.compile(IPV6_REGEX)
                matcher = pattern.matcher(ip)
                if (matcher.find() && matcher.groupCount() > 0 && !ipv4) {
                    return matcher.group(1)
                }
            }
        }
        return null
    }



    /**
     * Get IP address from first non-localhost interface
     *
     * @param useIPv4 true=return ipv4, false=return ipv6
     * @return address or empty string
     *
     *
     * http://stackoverflow.com/questions/6064510/how-to-get-ip-address-of-the-device
     */
    fun getIPAddress(useIPv4: Boolean): String {
        try {
            val interfaces: List<NetworkInterface> =
                Collections.list(NetworkInterface.getNetworkInterfaces())
            for (intf in interfaces) {
                val addrs: List<InetAddress> = Collections.list(intf.inetAddresses)
                for (addr in addrs) {
                    if (!addr.isLoopbackAddress) {
                        val sAddr = addr.hostAddress
                        val isIPv4 = sAddr.indexOf(':') < 0
                        if (useIPv4) {
                            if (isIPv4) {
                                return sAddr
                            }
                        } else {
                            if (!isIPv4) {
                                val delim = sAddr.indexOf('%') // drop ip6 zone suffix
                                return if (delim < 0) sAddr.uppercase(Locale.getDefault()) else sAddr.substring(
                                    0,
                                    delim
                                ).uppercase(
                                    Locale.getDefault()
                                )
                            }
                        }
                    }
                }
            }
        } catch (ex: java.lang.Exception) {
            // for now eat exceptions
            AppLog.error(this::class.java, "Une erreur est survenue.", ex)
        }
        return ""
    }


    fun isPortOpen(host: String, port: Int): Boolean = (try {
        AppLog.logDebutMethode(
            this,
            "${this::class.qualifiedName}.isPortOpen(host = $host, port = $port)"
        )
        Socket().use {
            it.connect(InetSocketAddress(host, port), 2000)
            true
        }
    } catch (e: Exception) {
        AppLog.error(this, "", e)
        false
    }).also {
        AppLog.logFinMethode(
            this,
            "${this::class.qualifiedName}.isPortOpen(host = $host, port = $port) return $it"
        )
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?.let { capabilities ->
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) or capabilities.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI
                    ) or capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                } ?: false

        } else {
            connectivityManager.activeNetworkInfo?.let { activeNetworkInfo ->
                activeNetworkInfo.isConnected
            } ?: false

        })
    }

}

enum class NetworkType {
    NETWORK_ACP, NETWORK_MOBILE, NETWORK_UNAVAILABLE, NETWORK_UNSUPPORTED
}