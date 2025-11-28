package ipn.upiita.mx.proyecto1.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ipn.upiita.mx.proyecto1.model.*
@Dao
interface TaskDao {

    /*insertar una task*/
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun InserTask(task: Task)

    /*obtener task por id*/
    @Query("SELECT * FROM task WHERE id =:id")
    suspend fun getTaskById(id :Int):Task?

    /*obtener tas por nombre*/
    @Query("SELECT * FROM task WHERE name = :name")
    suspend fun getTaskByName(name :String): Task?

    /*obtener todas las task*/
    @Query("SELECT * FROM task ")
    suspend fun getAllTask():List<Task>;

    /*insertar todas una lista de task*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertAllTask(Lista:List<Task>)

    /*eliminar una task*/
    @Delete()
    suspend fun deleteTask(task:Task)

    /*eliminar todas las tasks*/
    @Query("DELETE FROM task")
    suspend fun DeleteAllTask();

    /*actualizar tarea*/
    @Update
    suspend fun UpdateTask(task:Task);
}