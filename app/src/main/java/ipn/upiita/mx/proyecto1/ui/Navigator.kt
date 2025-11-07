package ipn.upiita.mx.proyecto1.ui

import android.annotation.SuppressLint
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ipn.upiita.mx.proyecto1.ui.*
import ipn.upiita.mx.proyecto1.viewModel.UserRegisterScreenViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ipn.upiita.mx.proyecto1.model.UserRepository
import ipn.upiita.mx.proyecto1.viewModel.LoginScreenViewModel
import ipn.upiita.mx.proyecto1.viewModel.ModifyMajorScreenViewModel
import ipn.upiita.mx.proyecto1.viewModel.UserViewModel
import ipn.upiita.mx.proyecto1.model.*
import ipn.upiita.mx.proyecto1.viewModel.*
import androidx.room.*
import androidx.compose.runtime.*
@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun Navigator( db : AppDatabase) {
    val navController = rememberNavController()
    // Crear el repositorio
    val repo = UserRepository(db.userDao())
    val usrViewModel = UserViewModel(repo)
    /*
    forma original
    val UsrRgstVM: UserRegisterScreenViewModel = viewModel()
    val LoginScreenVM: LoginScreenViewModel= viewModel()
    val mdfyMjrVM: ModifyMajorScreenViewModel =viewModel()
    */

    val LoginScreenVM = remember { LoginScreenViewModel(usrViewModel) }
    val mdfyMjrVM: ModifyMajorScreenViewModel = viewModel()


    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { LoginScreen(navController,LoginScreenVM) }
        composable("main_menu") { MainMenuScreen(navController) }
        composable("registro") { RegisterScreen(navController, usrViewModel) }
        composable("cambio_carrera"){ModifyMajorScreen(navController,mdfyMjrVM)}
        composable("UserListScreen"){ UserListScreen(viewModel = usrViewModel,
            onSearch={})}


    }
}