package ipn.upiita.mx.proyecto1.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ipn.upiita.mx.proyecto1.model.*
@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun InserTask(task: Task)

    @Query("SELECT * FROM task WHERE id =:id")
    suspend fun getTaskById(id :Int):Task?

    @Query("SELECT * FROM task WHERE name = :name")
    suspend fun getTaskByName(name :String): Task?

    @Query("SELECT * FROM task ")
    suspend fun getAllTask():List<Task>;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertAllTask(Lista:List<Task>)

    @Delete()
    suspend fun deleteTask(task:Task)

    @Query("DELETE FROM task")
    suspend fun DeleteAllTask();


}