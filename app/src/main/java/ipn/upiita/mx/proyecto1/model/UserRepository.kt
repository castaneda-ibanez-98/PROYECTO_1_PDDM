package ipn.upiita.mx.proyecto1.model
import android.util.Log
import ipn.upiita.mx.proyecto1.apiclient.UserApiService

class UserRepository(private val local: UserDao,
    private val remoto: UserApiService,
    private val modo:Boolean){

    private val token = "mi token"

    suspend fun insert(user: User) {
        Log.d("UserRepository",
            "iniciando funcion insert")
        if(modo){
            remoto.createUser("Bearer $token",user)
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

    suspend fun findByStudentId(boleta: String):User?{
        return if(modo){
            Log.d("UserRepository",
                "consultando en bd de api el estudiante con boleta $boleta")
            remoto.getUserById("Bearer $token",boleta)
        }else{
            Log.d("UserRepository",
                "consultando en bd local el estudiante con boleta : $boleta")
            local.getUserByStudentId(boleta)
        }
    }

    suspend fun update(user: User?) {
        if (modo) {
            Log.d("UserRepository",
                "actualizando usuario ${user?.boleta} de manera remota y local")
            user?.let { remoto.updateUser("Bearer $token",it.boleta,it) }
            user?.let { local.updateUser(it) }
        }else{
            Log.d("UserRepository",
                "actualizando usuario de manera local")
            user?.let { local.updateUser(it)}
        }
    }
}