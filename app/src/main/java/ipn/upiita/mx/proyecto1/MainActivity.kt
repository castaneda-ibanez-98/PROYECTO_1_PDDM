package ipn.upiita.mx.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ipn.upiita.mx.proyecto1.ui.theme.Proyecto1Theme
import androidx.room.*
import androidx.lifecycle.*
import androidx.compose.*
import kotlin.*
import ipn.upiita.mx.proyecto1.model.*
import ipn.upiita.mx.proyecto1.ui.*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "users.db")
            .allowMainThreadQueries() // ⚠️ Solo para pruebas
            .fallbackToDestructiveMigration()//igual pruebas
            .build()*/

        setContent {
            Navigator()
        }
    }
}