package ipn.upiita.mx.proyecto1.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ipn.upiita.mx.proyecto1.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    /*insertar una task*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

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
    suspend fun insertAllTask(Lista:List<Task>)

    /*eliminar una task*/
    @Delete()
    suspend fun deleteTask(task:Task)

    /*eliminar todas las tasks*/
    @Query("DELETE FROM task")
    suspend fun deleteAllTask();

    /*actualizar tarea*/
    @Update
    suspend fun updateTask(task:Task);

    /*FUNCIONES DEDICADAS A LA SINCRONIZACION*/

    @Query("SELECT * FROM task WHERE deleted = 0")
    fun observeTask(): Flow<List<Task>>;

    @Query("SELECT * FROM task WHERE pendingSync =1")
    suspend fun getPendingSync():List<Task>

    @Query("UPDATE task SET pendingSync = 0 WHERE id IN (:ids)")
    suspend fun clearPending(ids: List<Int>);

    @Query("UPDATE task SET deleted = 1, pendingSync = 1 WHERE id = :id")
    suspend fun softDelete(id: Int)

    /*FUNCION PARA OBTENER EL ID MINIMO EN EL SISTEMA*/
    @Query("SELECT MIN(id) FROM task")
    suspend fun getMinId(): Int?


    /*FUNCION creada para la eliminacion de  los creados con id's negativos*/
    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteById(id: Int)

}