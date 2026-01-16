package ipn.upiita.mx.proyecto1.model.sync

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.annotation.RequiresPermission


class NetworkObserver(context: Context) {
    private val cm = context.getSystemService(ConnectivityManager::class.java)
    var connected = false
        private set

    private val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) { connected = true }
        override fun onLost(network: Network) { connected = false }
    }

    init { cm.registerDefaultNetworkCallback(callback) }
}