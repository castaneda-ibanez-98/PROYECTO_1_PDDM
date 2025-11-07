package ipn.upiita.mx.proyecto1.model


import androidx.room.*
import ipn.upiita.mx.proyecto1.model.*

@Database(entities = [User::class,Task::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao


}