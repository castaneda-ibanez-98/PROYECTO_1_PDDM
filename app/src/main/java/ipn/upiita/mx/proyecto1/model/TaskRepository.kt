package ipn.upiita.mx.proyecto1.model

import android.util.Log
import ipn.upiita.mx.proyecto1.apiclient.NetworkHelper
import ipn.upiita.mx.proyecto1.apiclient.TaskApiService
import ipn.upiita.mx.proyecto1.viewModel.Sesion

class TaskRepository(private val local: TaskDao,
                     private val remoto: TaskApiService,
                     private val sesion: Sesion,
                     private val modo: Boolean){

        //suspend fun insert(task: Task) = local.InserTask(task)
        //suspend fun getAll() = local.getAllTask()
        //suspend fun findByName(name: String) = local.getTaskByName(name)
        //suspend fun findByTaskId(taskId: Int) = local.getTaskById(taskId)

        suspend fun findByName(name:String):Task?{
                Log.d("UserRepository",
                        "iniciando busqueda por nombre")
                return if(modo){
                        Log.d("UserRepository",
                                "buscando en api")
                        //remoto.getTaskById("Bearer $token",name)

                        TODO("crear metodo de busqueda en api por nombre")
                }else{
                        Log.d("UserRepository",
                                "busqueda por nombre en bd local")
                        local.getTaskByName(name)
                }
        }
        suspend fun findByTaskId(taskId: Int ):Task?{
                Log.d("TaskRepository",
                        "iniciando busqueda por id")
                val token = sesion.token.value
                return if(modo){
                val taskRemota = remoto.getTaskById("Bearer $token",taskId)
                        Log.d("TaskRepository",
                                "obtenido la task ${taskRemota?.name} de la api")
                        local.getTaskById(taskId)?.let {local.deleteTask(it)}
                        Log.d("TaskRepository",
                                "borrando la task si ya existe en la bd actual")
                        taskRemota?.let{local.InserTask(it)}
                        Log.d("TaskRepository",
                                "reemplazando en bd local")
                        taskRemota
                }else{
                        local.getTaskById(taskId)
                }
        }
        suspend fun insert(task:Task){
                Log.d("TaskRepository", "task insetada: ${task.name}")
                val token = sesion.token.value
                if(modo){
                        local.InserTask(task)
                        Log.d("UserRepository",
                                "insercion de la tarea en bd local realizada")
                        Log.d("UserRepository",
                                "insercion de la tarea en bd remota realizada con token $token")
                        remoto.createTask("Bearer $token",task)

                }
                else{
                        Log.d("UserRepository",
                                "insercion de la tarea ${task.name} en bd local realizada")
                        local.InserTask(task)
                }

        }

        suspend fun getAll():List<Task>{
                val token = sesion.token.value
                Log.d(
                        "UserRepository",
                        "iniciando obtencion de todas las tasks")
                return if(modo) {

                        Log.d("UserRepository",
                                "mandando peticion a la api")
                        val tasksRemotas = remoto.getTasks("Bearer $token")
                        local.DeleteAllTask()
                        Log.d("UserRepository",
                                "eliminaccion de bd local realizada")
                        local.InsertAllTask(tasksRemotas)
                        Log.d("UserRepository",
                                "reemplazo en bd local realizada")
                        tasksRemotas
                }else{
                        Log.d("UserRepository",
                                "obteniendo tareas de la bd local")
                        local.getAllTask()
                }

        }
        suspend fun delete(task:Task){
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
        suspend fun update(task:Task){
                val token = sesion.token.value
                Log.d("TaskRepository",
                        "iniciando la actualizacion de la tarea: ${task.name}")
                if(modo){
                        remoto.updateTask("Bearer $token",task.id,task)
                        local.UpdateTask(task)
                }else{
                        Log.d("TaskRepository",
                                "actualizando la task ${task.name} localmente")
                        local.UpdateTask(task)
                }
        }

}