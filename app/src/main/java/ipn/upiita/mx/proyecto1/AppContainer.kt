package ipn.upiita.mx.proyecto1

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import ipn.upiita.mx.proyecto1.apiclient.RetrofitClient
import ipn.upiita.mx.proyecto1.model.* // Ajusta tus imports
import ipn.upiita.mx.proyecto1.model.repositorios.TaskRepository
import ipn.upiita.mx.proyecto1.model.sync.SyncPrefs
import ipn.upiita.mx.proyecto1.viewModel.Sesion

import androidx.work.*
import ipn.upiita.mx.proyecto1.model.sync.TaskSyncWorker


object AppContainer {
    lateinit var taskRepository: TaskRepository
        private set

    fun initialize(context: Context, sesion: Sesion) {
        if (::taskRepository.isInitialized) return
        val db = DatabaseClient.getDatabase(context)
        val syncPrefs = SyncPrefs(context)
        taskRepository = TaskRepository(
            local = db.taskDao(),
            remoto = RetrofitClient.taskApi,
            sesion = sesion,
            syncPrefs = syncPrefs
        )
    }


    fun scheduleSync(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncRequest = OneTimeWorkRequestBuilder<TaskSyncWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            "SyncTasksNow",
            ExistingWorkPolicy.KEEP,
            syncRequest
        )
    }
}