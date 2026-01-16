package ipn.upiita.mx.proyecto1.model.repositorios

import android.util.Log
import androidx.room.Update
import ipn.upiita.mx.proyecto1.apiclient.UserApiService
import ipn.upiita.mx.proyecto1.model.DTO.UserDTO
import ipn.upiita.mx.proyecto1.model.request.LoginRequest
import ipn.upiita.mx.proyecto1.model.response.LoginResponse
import ipn.upiita.mx.proyecto1.model.User
import ipn.upiita.mx.proyecto1.model.UserDao
import ipn.upiita.mx.proyecto1.model.request.CreateUserRequest
import ipn.upiita.mx.proyecto1.model.request.ForgotPasswordRequest
import ipn.upiita.mx.proyecto1.model.request.UpdatePasswordRequest
import ipn.upiita.mx.proyecto1.viewModel.Sesion
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class UserRepository(private val remoto: UserApiService,
                     private val sesion: Sesion,
                     private val modo:Boolean) {


    suspend fun Login(email: String, password: String) {
        Log.d("UserRepository", "iniciando sesion")
        var response: LoginResponse
        if (modo) {
            val response = remoto.login(LoginRequest(email, password))
            Log.d("UserRepository", "OK: $response")
            Log.d("UserRepository", "se obtuvo token ${sesion.token.value}")
            sesion.login(response.user, response.token)

        }else{
        }
    }


    suspend fun insert(user: CreateUserRequest) {
        Log.d(
            "UserRepository",
            "iniciando funcion insert"
        )
        if (modo) {
            remoto.createUser(user)
            Log.d(
                "UserRepository",
                "Usuario insertado: ${user.nombre} en bd exterma"
            )
        } else {
            Log.d(
                "UserRepository",
                "Usuario insertado: ${user.nombre} en bd local"
            )
        }
    }


    suspend fun getAll(): List<UserDTO> {
        val token = sesion.token.value
        Log.d(
            "UserRepository",
            "iniciando metodo de obtencion de usuarios"
        )

            val usuarios = remoto.getUsers("Bearer $token")
          return  usuarios
    }

    suspend fun findByEmail(email: String): UserDTO? {
        val token = sesion.token.value
        Log.d(
            "UserRepository",
            "consultando en bd de api"
        )
        return remoto.getUserByEmail("Bearer $token", email)
    }

    suspend fun findByStudentId(id: Int): UserDTO? {
        val token = sesion.token.value
        Log.d(
            "UserRepository",
            "consultando en bd de api el estudiante con id $id"
        )
        return remoto.getUserById("Bearer $token", id)

    }

    suspend fun update(user: UserDTO?) {
        val token = sesion.token.value
        val userId = sesion.userActual.value
        Log.d(
            "UserRepository",
            "actualizando usuario ${user?.boleta} de manera remota"
        )
        if(user!=null && userId!=null)
         { remoto.updateUser("Bearer $token", userId.id, user) }
    }

    suspend fun updatePassword(request: UpdatePasswordRequest){
        val  token= sesion.token.value
        val user = sesion.userActual.value
        if (user!=null){
            Log.d(
                "UserRepository",
                "actualizando usuario ${user.nombre} de manera remota"
            )
            remoto.updatePassword("Bearer $token",user.id,request)
        }
    }

    suspend fun obtenerActualizado(user: UserDTO?): UserDTO?{
            val token = sesion.token.value
            val userId = sesion.userActual.value
        var actualizado=sesion.userActual.value
        Log.d(
                "UserRepository",
                "actualizando usuario ${user?.boleta} de manera remota"
            )
            if(user!=null && userId!=null)
            { actualizado = remoto.getUserById("Bearer $token", userId.id) }
    return actualizado
    }

    suspend fun forgotPassword(request: ForgotPasswordRequest){
        val  token= sesion.token.value
        val user = sesion.userActual.value
        if (user!=null){
            Log.d(
                "UserRepository",
                "actualizando usuario ${user.nombre} de manera remota"
            )
            remoto.forgotPassword("Bearer $token",user.id,request)
        }
    }

}