package ipn.upiita.mx.proyecto1.model


import androidx.room.*
import ipn.upiita.mx.proyecto1.model.*

@Database(entities = [Task::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}