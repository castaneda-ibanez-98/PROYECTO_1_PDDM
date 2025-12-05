package ipn.upiita.mx.proyecto1.apiclient

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.*
import ipn.upiita.mx.proyecto1.model.*


interface TaskApiService {

    /*obtener la lista completa de tasks*/
    @GET("tasks")
    suspend fun getTasks(
        @Header("Authorization") token: String
    ): List<Task>

    /*obtener una sola tarea*/
    @GET("tasks")
    suspend fun getTaskById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Task?

    /*crear una sona instancia de task*/
    @POST("tasks")
    suspend fun createTask(
        @Header("Authorization") token: String,
        @Body task: Task
    ): Task



    /*actualizar una una tarea*/
    @PUT("tasks/{id}")
    suspend fun updateTask(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body task: Task
    ): Task

    @DELETE("tasks/{id}")
    suspend fun deleteTask(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Unit





}