package ipn.upiita.mx.proyecto1.model

import android.content.Context
import androidx.room.Room

object DatabaseClient {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "proyecto1.db")
                    .allowMainThreadQueries() // ⚠️ Solo para pruebas
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
}

