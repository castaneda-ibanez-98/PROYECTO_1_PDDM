package ipn.upiita.mx.proyecto1.model.sync

import android.content.Context


class SyncPrefs(context: Context) {
    private val prefs = context.getSharedPreferences("sync", Context.MODE_PRIVATE)
    fun getLastSync() = prefs.getLong("last_sync", 0)
    fun setLastSync(v: Long) = prefs.edit().putLong("last_sync", v).apply()
}