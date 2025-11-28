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
import ipn.upiita.mx.proyecto1.apiclient.RetrofitClient
import ipn.upiita.mx.proyecto1.apiclient.TaskApiService

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun Navigator() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val db = remember{DatabaseClient.getDatabase(context = context)}

    // Creamos los repositorios
    val userRepo = UserRepository(
        local=db.userDao(),
        remoto = RetrofitClient.userApi,
        modo=false)

    val taskRepo = TaskRepository(
        local=db.taskDao(),
        remoto= RetrofitClient.taskApi,
        modo = false)


    val usrViewModel = UserViewModel(userRepo)
    val tskViewModel = TaskViewModel(taskRepo)

    /*
    forma original
    val UsrRgstVM: UserRegisterScreenViewModel = viewModel()
    val LoginScreenVM: LoginScreenViewModel= viewModel()
    val mdfyMjrVM: ModifyMajorScreenViewModel =viewModel()
    */

    val LoginScreenVM = remember { LoginScreenViewModel(usrViewModel) }
    val mdfyMjrVM: ModifyMajorScreenViewModel = viewModel()
    val frgtPassword = remember { ForgotPasswordScreenViewModel(usrViewModel) }

    NavHost(navController = navController, startDestination = "inicio") {

        composable("inicio") { LoginScreen(navController,usrViewModel) }
        composable("main_menu") { MainMenuScreen(navController) }
        composable("registro") { RegisterScreen(navController, usrViewModel) }
        composable("cambio_carrera"){ModifyMajorScreen(navController,mdfyMjrVM)}
        composable("UserListScreen"){ UserListScreen(viewModel = usrViewModel,
            onSearch={})}
        composable("TaskListScreen"){ TaskListScreen(
            navController = navController,
            viewModel = tskViewModel,
            onSearch={})}
        composable("agregar_tarea"){TaskRegisterScreen(navController,tskViewModel)}
        composable("olvido-contrasena"){ForgotPasswordScreen1(navController,usrViewModel)}
        composable("olvido-contrasena2"){ForgotPasswordScreen2(navController,usrViewModel)}


    }
}