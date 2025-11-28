package ipn.upiita.mx.proyecto1.model

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Update

@Dao
interface UserDao{

    /*insertar usuario*/
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    /*obtener usuario por id*/
    @Query("SELECT * FROM user WHERE boleta =:boleta")
    suspend fun getUserByStudentId(boleta :String):User?

    /*obtener usuario por email*/
    @Query("SELECT * FROM user WHERE correo = :correo")
    suspend fun getUserByEmail(correo: String): User?

    /*obtener todos los usuarios*/
    @Query("SELECT * FROM user ")
    suspend fun getAllUsers():List<User>;

    /*insertar todas una lista de usuarios*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertAllUsers(Lista:List<User>)

    /*eliminar todas las tasks*/
    @Query("DELETE FROM user")
    suspend fun DeleteAllUsers();

    /*modificar un usuario*/
    @Update
    suspend fun updateUser(user:User)

    /*eliminar usuario, pasando el usuario*/
    @Delete
    suspend fun deleteUser(user:User)




}