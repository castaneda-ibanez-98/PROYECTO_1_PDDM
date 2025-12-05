package ipn.upiita.mx.proyecto1.model
import android.util.Log
import androidx.lifecycle.viewmodel.compose.viewModel
import ipn.upiita.mx.proyecto1.apiclient.UserApiService
import ipn.upiita.mx.proyecto1.viewModel.Sesion
import okhttp3.MediaType
import retrofit2.Response
import okhttp3.ResponseBody
import okhttp3.MediaType.*
import retrofit2.HttpException


class UserRepository(private val local: UserDao,
                     private val remoto: UserApiService,
                     private val sesion: Sesion,
                     private val modo:Boolean) {


    suspend fun Login(email:String,password:String){
    Log.d("UserRepository","iniciando sesion")
        var response: LoginResponse
        if(modo) {
                val response = remoto.login(LoginRequest(email, password))
                Log.d("UserRepository", "OK: $response")
                Log.d("UserRepository","se obtuvo token ${sesion.token.value}")
            sesion.login(response.user, response.token)
        }else{
            Log.d("UserRepository","iniciando sesion de manera local")
            if(findByEmail(email)==null){
                response = LoginResponse("",null)
                Log.d("UserRepository",
                    "sesion no pudo ser iniciada, no se encontro el usuario")
                val respuesta = Response.error<String>(
                    401,
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                        "{\"message\":\"usuario no encontrado\"}"
                    )
                )
                throw HttpException(respuesta)
            }else{ if(password != findByEmail(email)?.contrasena){
                /*<><><><><><><>SIMULACION DE THROW<><><><><>*/
                val respuesta = Response.error<String>(
                    401,
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                        "{\"message\":\"contraseña incorrecta\"}"
                    )
                )
                throw HttpException(respuesta)
                /*<><><><><><>*/
            }
                else{
                response = LoginResponse("",findByEmail(email))
                Log.d("UserRepository",
                    "sesion iniciada con usuario ${response.user?.nombre} e id: " +
                            "${response.user?.id}")
                sesion.login(response.user,"")}
            }
        }
    }


    suspend fun insert(user: User) {
        Log.d("UserRepository",
            "iniciando funcion insert")
        if(modo){
            remoto.createUser(user)
            local.insertUser(user)
            Log.d("UserRepository",
                "Usuario insertado: ${user.nombre} en bd exterma")
        }else {
             Log.d("UserRepository",
                 "Usuario insertado: ${user.nombre} en bd local")
             local.insertUser(user)
         }
         }


    suspend fun getAll(): List<User> {
        val token = sesion.token.value
        Log.d("UserRepository",
            "iniciando metodo de obtencion de usuarios")
        return if(modo){
            val usuarios = remoto.getUsers("Bearer $token")
            usuarios
        }else {
            val lista = local.getAllUsers()
            Log.d("UserRepository", "Usuarios en BD: ${lista.size}")
        lista
        }
    }

    suspend fun findByEmail(email: String):User?{
        val token = sesion.token.value
        return if(modo){
            Log.d("UserRepository",
                "consultando en bd de api")
             remoto.getUserByEmail("Bearer $token",email)
        }else{
            Log.d("UserRepository",
                "consultando en bd local")
          local.getUserByEmail(email)
        }
    }

    suspend fun findByStudentId(id: Int):User?{
        val token = sesion.token.value
        return if(modo){
            Log.d("UserRepository",
                "consultando en bd de api el estudiante con id $id")
            remoto.getUserById("Bearer $token",id)
        }else{
            Log.d("UserRepository",
                "consultando en bd local el estudiante con id: $id")
            local.getUserByStudentId(id)
        }
    }

    suspend fun update(user: User?) {
        val token = sesion.token.value
        if (modo) {
            Log.d("UserRepository",
                "actualizando usuario ${user?.boleta} de manera remota y local")
            user?.let { remoto.updateUser("Bearer $token",it.id,it) }
            user?.let { local.updateUser(it) }
        }else{
            Log.d("UserRepository",
                "actualizando usuario de manera local")
            user?.let { local.updateUser(it)}
        }
    }
}