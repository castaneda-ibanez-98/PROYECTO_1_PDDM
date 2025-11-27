package ipn.upiita.mx.proyecto1.model

import ipn.upiita.mx.proyecto1.apiclient.NetworkHelper
import ipn.upiita.mx.proyecto1.apiclient.TaskApiService

class TaskRepository(private val local: TaskDao,
                     private val remoto: TaskApiService,
                     private val modo: Boolean){

        private val token = "tu_token_aqui"
        //suspend fun insert(task: Task) = local.InserTask(task)
        //suspend fun getAll() = local.getAllTask()
        suspend fun findByName(name: String) = local.getTaskByName(name)
        suspend fun findByTaskId(taskId: Int) = local.getTaskById(taskId)

        suspend fun insert(task:Task){

                if(modo){
                        local.InserTask(task)
                        remoto.createTask("Bearer $token",task)
                }
                else{
                        local.InserTask(task)
                }

        }

        suspend fun getAll():List<Task>{
                return if(modo) {
                        val tasksRemotas = remoto.getTasks("Bearer $token")
                        local.DeleteAllTask()
                        local.InsertAllTask(tasksRemotas)
                        tasksRemotas
                }else{
                        local.getAllTask()
                }

        }
        suspend fun delete(task:Task){
                if(modo){
                        val taskRemota = remoto.deleteTask("Bearer $token",task.id)
                        local.deleteTask(task)
                }
                else{
                        local.deleteTask(task)
                }
        }


}