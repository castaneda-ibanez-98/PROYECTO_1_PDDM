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

    // Esta función se llamará UNA sola vez cuando inicie la app
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
        // 1. Definimos la restricción: "SOLO EJECUTAR SI HAY INTERNET"
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // 2. Creamos una solicitud de UNA SOLA VEZ (OneTime)
        val syncRequest = OneTimeWorkRequestBuilder<TaskSyncWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            "SyncTasksNow",
            ExistingWorkPolicy.KEEP,/*para que no registre mas de una vez en caso de hacer la accion mas de una vez*/
            syncRequest
        )
    }
}