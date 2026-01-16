package ipn.upiita.mx.proyecto1.model.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ipn.upiita.mx.proyecto1.AppContainer


class TaskSyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val repo = AppContainer.taskRepository

        return try {
            repo.sync()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}

