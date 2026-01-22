package ipn.upiita.mx.proyecto1.apiclient

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.*
import ipn.upiita.mx.proyecto1.model.*
import ipn.upiita.mx.proyecto1.model.DTO.SyncResponseDTO
import ipn.upiita.mx.proyecto1.model.DTO.TaskDTO


interface TaskApiService {

    /*obtener la lista completa de tasks*/
    @GET("tasks")
    suspend fun getTasks(
        @Header("Authorization") token: String
    ): List<Task>

    /*obtener la lista de tasks por usuario*/
    @GET("tasks/user/{id}")
    suspend fun getTasksByUser(
        @Header("Authorization") token: String,
        @Path("id") userId: Int
    ): List<Task>



    /*obtener una sola tarea*/
    @GET("tasks/{id}")
    suspend fun getTaskById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Task?


    /*crear una sona instancia de task*/
    @POST("tasks")
    suspend fun createTask(
        @Header("Authorization") token: String,
        @Body task: TaskDTO
    ): Task



    /*actualizar una una tarea*/
    @PUT("tasks/{id}")
    suspend fun updateTask(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body task: TaskDTO
    ): Task

    @DELETE("tasks/{id}")
    suspend fun deleteTask(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Unit

    /*SECCION DE SINCRONIZACION*/
    @GET("tasks")
    suspend fun getAll(): List<TaskDTO>

    @POST("tasks/sync")
    suspend fun sync(
        @Header("Authorization") token: String,
        @Body client: List<TaskDTO>): SyncResponseDTO
}