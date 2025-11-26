package ipn.upiita.mx.proyecto1.model
import android.util.Log
class UserRepository(private val dao: UserDao) {
    suspend fun insert(user: User) {
        Log.d("UserRepository", "Usuario insertado: ${user.nombre}")
        dao.insertUser(user)}
    //suspend fun getAll() = dao.getAllUsers()
    suspend fun getAll(): List<User> {
        val lista = dao.getAllUsers()
        Log.d("UserRepository", "Usuarios en BD: ${lista.size}")
        return lista
    }

    suspend fun findByEmail(email: String) = dao.getUserByEmail(email)
    suspend fun findByName(name: String) = dao.getUserByName(name)
    suspend fun findByStudentId(studentId: String) = dao.getUserByStudentId(studentId)

    suspend fun update(user: User?) {
        user?.let { dao.updateUser(it) } }

}