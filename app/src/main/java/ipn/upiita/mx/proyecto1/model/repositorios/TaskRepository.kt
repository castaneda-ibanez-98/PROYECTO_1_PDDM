package ipn.upiita.mx.proyecto1.model.repositorios

import android.util.Log
import ipn.upiita.mx.proyecto1.apiclient.TaskApiService
import ipn.upiita.mx.proyecto1.model.CrearTask
import ipn.upiita.mx.proyecto1.model.DTO.ConflictDTO
import ipn.upiita.mx.proyecto1.model.Task
import ipn.upiita.mx.proyecto1.model.TaskDao
import ipn.upiita.mx.proyecto1.model.request.UpdateTaskRequest
import ipn.upiita.mx.proyecto1.model.sync.SyncPrefs
import ipn.upiita.mx.proyecto1.model.sync.toDTO
import ipn.upiita.mx.proyecto1.model.sync.toEntity
import ipn.upiita.mx.proyecto1.viewModel.Sesion

class TaskRepository(private val local: TaskDao,
                     private val remoto: TaskApiService,
                     private val sesion: Sesion,
                     private val syncPrefs: SyncPrefs){



        val tasks = local.observeTask()


        suspend fun addOrUpdate(taskLocal: Task) {
                local.insertTask(taskLocal.copy(
                        updatedAt = System.currentTimeMillis(),
                        pendingSync = true
                ))
        }

        suspend fun delete(taskLocal: Task) {
                local.insertTask(taskLocal.copy(
                        deleted = true,
                        updatedAt = System.currentTimeMillis(),
                        pendingSync = true
                ))
        }

        suspend fun sync() {
                val currentToken = sesion.token.value
                if (currentToken == null) {
                        Log.e("TaskRepo", "No hay token, no se puede sincronizar")
                        return
                }
                val authHeader = "Bearer $currentToken"
                val pending = local.getPendingSync()
                val response = remoto.sync(authHeader,pending.map { it.toDTO() })
                response.serverUpdates.forEach { serverTaskDto ->
                        if (serverTaskDto.tempId != null && serverTaskDto.tempId < 0) {
                                local.deleteById(serverTaskDto.tempId)
                        }
                }
                local.insertAllTask(response.serverUpdates.map { it.toEntity() })
                response.conflicts.forEach { handleConflict(it) }
                local.clearPending(pending.map { it.id })
                syncPrefs.setLastSync(System.currentTimeMillis())
        }



        private suspend fun handleConflict(c: ConflictDTO) {
                val server = c.server.toEntity()
                val client = c.client.toEntity()

                val result = when {
                        client.updatedAt > server.updatedAt ->
                                client.copy(pendingSync = true)
                        server.updatedAt > client.updatedAt ->
                                server
                        else ->
                                mergeTasks(server, client)
                }
                local.insertTask(result)
        }

        private fun mergeTasks(server: Task, client: Task) =
                Task(
                        id = server.id,
                        userId = client.userId.takeIf { client.updatedAt>=server.updatedAt }?:server.userId,
                        name = client.name.takeIf { client.updatedAt >= server.updatedAt } ?: server.name,
                        status = client.status.takeIf { client.updatedAt >= server.updatedAt } ?: server.status,
                        deadline = client.deadline.takeIf { client.updatedAt >= server.updatedAt } ?: server.deadline,
                        updatedAt = maxOf(server.updatedAt, client.updatedAt),
                        pendingSync = true
                )


        suspend fun findByName(name:String): Task?{
                Log.d("UserRepository",
                        "iniciando busqueda por nombre")
                val lista = local.getTaskByName(name)
                return lista
                }

        suspend fun findByTaskId(taskId: Int ): Task?{
                Log.d("TaskRepository",
                        "iniciando busqueda por id")
                val token = sesion.token.value
                val taskLocal = local.getTaskById(taskId)
                        Log.d("TaskRepository",
                                "reemplazando en bd local")
                        return taskLocal
        }
        suspend fun insert(crearTask: CrearTask){
                Log.d("TaskRepository", "task insertada: ${crearTask.name}")
                val token = sesion.token.value
                val user = sesion.userActual.value
                val now = System.currentTimeMillis()
                if(user!=null) {
                        val task = Task(
                                id = generateLocalId(),
                                userId = user.id,
                                name = crearTask.name,
                                deadline = crearTask.deadline,
                                status = crearTask.status,
                                updatedAt = now,
                                pendingSync = true,
                                deleted = false
                        )
                        local.insertTask(task)
                                Log.d("UserRepository","insercion de la tarea en bd local realizada")
                }
        }

        /*funcion para generar un id minimo que sea corregido en el backend*/
        suspend fun generateLocalId(): Int {
                return local.getMinId()?.minus(1) ?: -1
        }

        suspend fun getAll():List<Task>{
                val token = sesion.token.value
                Log.d(
                        "UserRepository",
                        "iniciando obtencion de todas las tasks")

                        Log.d("UserRepository",
                                "obteniendo tareas de la bd local")
                return  local.getAllTask()
        }

        suspend fun update(viejo: UpdateTaskRequest){
                val token = sesion.token.value
                val user = sesion.userActual.value
                if(user!=null) {
                        var actualizado = Task(
                                viejo.id,
                                user.id,
                                viejo.name,
                                deadline = viejo.deadline,
                                status = viejo.status,
                                updatedAt = System.currentTimeMillis(),
                                pendingSync = true
                        )
                        local.insertTask(actualizado)
                        Log.d(
                                "TaskRepository",
                                "actualizando la viejo ${viejo.name} localmente"
                        )
                }
                }




}
/*
       suspend fun delete(task: Task){
                Log.d("TaskRepository", "task eliminada: ${task.name}")
                val token = sesion.token.value
                if(modo){
                        Log.d("TaskRepository", "eliminando registro en la api")
                        val taskRemota = remoto.deleteTask("Bearer $token",task.id)
                        Log.d("TaskRepository", "eliminando registro en la bd local")
                        local.deleteTask(task)
                }
                else{
                        Log.d("TaskRepository", "eliminando registro unicamente en la bd local")
                        local.deleteTask(task)
                }
        }
        */

/*zona donde esta lo de sincronizacion del profesor*/

/*
class TaskRepository(
    private val dao: TaskDAO,
    private val api: TaskApi,
    private val prefs: SyncPrefs
) {

    val tasks = dao.observeTasks()

    suspend fun addOrUpdate(task: TaskEntity) {
        dao.insert(task.copy(
            updatedAt = System.currentTimeMillis(),
            pendingSync = true
        ))
    }

    suspend fun delete(task: TaskEntity) {
        dao.insert(task.copy(
            deleted = true,
            updatedAt = System.currentTimeMillis(),
            pendingSync = true
        ))
    }

    suspend fun sync() {
        val pending = dao.getPendingSync()

        val response = api.sync(pending.map { it.toDTO() })

        dao.insertAll(response.serverUpdates.map { it.toEntity() })

        response.conflicts.forEach { handleConflict(it) }

        dao.clearPending(pending.map { it.id })
        prefs.setLastSync(System.currentTimeMillis())
    }

    private suspend fun handleConflict(c: ConflictDTO) {
        val server = c.server.toEntity()
        val client = c.client.toEntity()

        val result = when {
            client.updatedAt > server.updatedAt ->
                client.copy(pendingSync = true)
            server.updatedAt > client.updatedAt ->
                server
            else ->
                mergeTasks(server, client)
        }
        dao.insert(result)
    }

    private fun mergeTasks(server: TaskEntity, client: TaskEntity) =
        TaskEntity(
            id = server.id,
            name = client.name.takeIf { client.updatedAt >= server.updatedAt } ?: server.name,
            status = client.status.takeIf { client.updatedAt >= server.updatedAt } ?: server.status,
            deadline = client.deadline.takeIf { client.updatedAt >= server.updatedAt } ?: server.deadline,
            updatedAt = maxOf(server.updatedAt, client.updatedAt),
            pendingSync = true
        )
}
 */
