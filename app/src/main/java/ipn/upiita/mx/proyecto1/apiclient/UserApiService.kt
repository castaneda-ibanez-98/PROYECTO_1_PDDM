package ipn.upiita.mx.proyecto1.apiclient

import ipn.upiita.mx.proyecto1.model.LoginRequest
import ipn.upiita.mx.proyecto1.model.LoginResponse
import ipn.upiita.mx.proyecto1.model.User
import retrofit2.http.*


interface UserApiService {

    /*LOGIN*/
    @POST("auth/login")
    suspend fun login(
        @Body request : LoginRequest
    ): LoginResponse

    /*obtener todos los usuarios*/
    @GET("user")
    suspend fun getUsers(
        @Header("Authorization") token: String
    ):List<User>

    /*obtener usuario por id*/
    @GET("user/{id}")
    suspend fun getUserById(
        @Header("Authorization")token:String,
        @Path("id")id:Int
    ):User?

    /*obtener usuario por correo*/
    @GET("user/{correo}")
    suspend fun getUserByEmail(
        @Header("Authorization")token:String,
        @Path("correo")correo:String
    ):User?

    /*crear usuario*/
    @POST("user")
    suspend fun createUser(
        @Body user:User
    ):User

    /*actualizar usuario*/
    @PUT("user/{id}")
    suspend fun updateUser(
        @Header("Authorization")token: String,
        @Path("id")id:Int,
        @Body user:User
    ):User

    /*borrar un usuario*/
    @DELETE("user/{id}")
    suspend fun deleteUser(
        @Header("Authorization")token:String,
        @Path("id")id:Int
    ):Unit

}