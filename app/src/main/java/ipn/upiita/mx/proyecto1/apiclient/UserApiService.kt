package ipn.upiita.mx.proyecto1.apiclient

import androidx.room.Update
import ipn.upiita.mx.proyecto1.model.DTO.SyncResponseDTO
import ipn.upiita.mx.proyecto1.model.DTO.TaskDTO
import ipn.upiita.mx.proyecto1.model.DTO.UserDTO
import ipn.upiita.mx.proyecto1.model.request.LoginRequest
import ipn.upiita.mx.proyecto1.model.response.LoginResponse
import ipn.upiita.mx.proyecto1.model.User
import ipn.upiita.mx.proyecto1.model.request.CreateUserRequest
import ipn.upiita.mx.proyecto1.model.request.ForgotPasswordRequest
import ipn.upiita.mx.proyecto1.model.request.UpdatePasswordRequest
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
    ):List<UserDTO>

    /*obtener usuario por id*/
    @GET("user/id/{id}")
    suspend fun getUserById(
        @Header("Authorization")token:String,
        @Path("id")id:Int
    ):UserDTO?

    /*obtener usuario por correo*/
    @GET("user/email/{correo}")
    suspend fun getUserByEmail(
        @Header("Authorization")token:String,
        @Path("correo")correo:String
    ):UserDTO?

    /*crear usuario*/
    @POST("user")
    suspend fun createUser(
        @Body user: CreateUserRequest
    ):UserDTO

    /*actualizar usuario*/
    @PUT("user/{id}")
    suspend fun updateUser(
        @Header("Authorization")token: String,
        @Path("id")id:Int,
        @Body user:UserDTO
    ):UserDTO



    /*borrar un usuario*/
    @DELETE("user/{id}")
    suspend fun deleteUser(
        @Header("Authorization")token:String,
        @Path("id")id:Int
    ):Unit

    @PUT("user/{id}/updatePassword")
    suspend fun updatePassword(
        @Header("Authorization")token: String,
        @Path("id")id: Int,
        @Body request: UpdatePasswordRequest
    ):Unit

    @PUT("user/{id}/forgotPassword")
    suspend fun forgotPassword(
        @Header("Authorization")token: String,
        @Path("id")id: Int,
        @Body request: ForgotPasswordRequest
    ):Unit







}