package ipn.upiita.mx.proyecto1.model

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Dao
import androidx.room.Update

@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE boleta =:boleta")
    suspend fun getUserByStudentId(boleta :String):User?

    @Query("SELECT * FROM user WHERE correo = :correo")
    suspend fun getUserByEmail(correo: String): User?

    @Query("SELECT * FROM user WHERE nombre = :nombre")
    suspend fun getUserByName(nombre:String):User?

    @Query("SELECT * FROM user ")
    suspend fun getAllUsers():List<User>;

    @Update
    suspend fun updateUser(user:User)

}