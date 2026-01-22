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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.*
import ipn.upiita.mx.proyecto1.model.*
import ipn.upiita.mx.proyecto1.ui.*
import ipn.upiita.mx.proyecto1.viewModel.Sesion

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
            /*siguientes 4 lineas agregadas asi como añadiendo
            * sesion como parametro para navigator*/
            val context = LocalContext.current
            val sesion: Sesion = viewModel()

            AppContainer.initialize(applicationContext, sesion)

            Navigator(sesion)
        }
    }
}

