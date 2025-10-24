package ipn.upiita.mx.proyecto1.model

class UserRepository(private val dao: UserDao) {
    suspend fun insert(user: User) = dao.insertUser(user)
    suspend fun getAll() = dao.getAllUsers()
    suspend fun findByEmail(email: String) = dao.getUserByEmail(email)
    suspend fun findByName(name: String) = dao.getUserByName(name)
    suspend fun findByStudentId(studentId: String) = dao.getUserByStudentId(studentId)

}