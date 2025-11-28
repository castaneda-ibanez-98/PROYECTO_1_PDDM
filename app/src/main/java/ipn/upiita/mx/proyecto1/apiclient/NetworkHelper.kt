package ipn.upiita.mx.proyecto1.apiclient

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission

/*clase creada con chatGPT*/
/*el concepto de esta clase es un objeto que nos ayudara a determinar
* si existe conexion a internet, si si, las funciones obtenidas del repositorio seran
* las remotas, si no se utilizaran las locales*/
class  NetworkHelper(private val context: Context) {
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = cm.activeNetwork ?: return false
        val actNw = cm.getNetworkCapabilities(nw) ?: return false
        return actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}

