package ipn.upiita.mx.proyecto1.model


import androidx.room.*
import ipn.upiita.mx.proyecto1.model.*

@Database(entities = [User::class,Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao


}