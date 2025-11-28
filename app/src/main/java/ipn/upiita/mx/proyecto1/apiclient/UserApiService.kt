package ipn.upiita.mx.proyecto1.apiclient

import ipn.upiita.mx.proyecto1.model.User
import retrofit2.http.*


interface UserApiService {

    /*obtener todos los usuarios*/
    @GET(".")
    suspend fun getUsers(
        @Header("Authorization") token: String
    ):List<User>

    /*obtener usuario por id*/
    @GET(".")
    suspend fun getUserById(
        @Header("Authorization")token:String,
        @Path("id")id:String
    ):User?

    /*obtener usuario por correo*/
    @GET(".")
    suspend fun getUserByEmail(
        @Header("Authorization")token:String,
        @Path("email")email:String
    ):User?

    /*crear usuario*/
    @POST(".")
    suspend fun createUser(
        @Header("Authorization")token:String,
        @Body user:User
    ):User

    /*actualizar usuario*/
    @PUT(".")
    suspend fun updateUser(
        @Header("Authorization")token: String,
        @Path("id")id:String,
        @Body user:User
    ):User

    /*borrar un usuario*/
    @DELETE("users/{id}")
    suspend fun deleteUser(
        @Header("Authorization")token:String,
        @Path("id")id:String
    ):Unit






}