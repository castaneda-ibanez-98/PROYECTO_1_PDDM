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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ipn.upiita.mx.proyecto1.apiclient.RetrofitClient
import ipn.upiita.mx.proyecto1.apiclient.TaskApiService

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun Navigator() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val modo = true

    val db = remember{DatabaseClient.getDatabase(context = context)}
    val sesion : Sesion = viewModel()
    // Creamos los repositorios
    val userRepo = UserRepository(
        local=db.userDao(),
        remoto = RetrofitClient.userApi,
        sesion = sesion,
        modo=modo)

    val taskRepo = TaskRepository(
        local=db.taskDao(),
        remoto= RetrofitClient.taskApi,
        sesion = sesion,
        modo = modo)



    val usrViewModel = UserViewModel(userRepo)
    val tskViewModel = TaskViewModel(taskRepo)

    /*
    forma original
    val UsrRgstVM: UserRegisterScreenViewModel = viewModel()
    val LoginScreenVM: LoginScreenViewModel= viewModel()
    val mdfyMjrVM: ModifyMajorScreenViewModel =viewModel()
    */

    val LoginScreenVM = remember { LoginScreenViewModel(usrViewModel,sesion) }
    val mdfyMjrVM = remember { ModifyMajorScreenViewModel(usrViewModel,sesion) }
    val frgtPassword = remember { ForgotPasswordScreenViewModel(usrViewModel) }

    NavHost(navController = navController, startDestination = "inicio") {

        composable("inicio") { LoginScreen(navController,usrViewModel,sesion) }
        composable("main_menu") { MainMenuScreen(navController,sesion) }
        composable("registro") { RegisterScreen(navController, usrViewModel) }
        composable("cambio_carrera"){ModifyMajorScreen(navController,mdfyMjrVM,sesion)}
        composable("UserListScreen"){ UserListScreen(viewModel = usrViewModel,
            onSearch={})}
        composable("TaskListScreen"){ TaskListScreen(
            navController = navController,
            viewModel = tskViewModel,
            onSearch={})}
        composable("agregar_tarea"){TaskRegisterScreen(navController,tskViewModel)}
        composable("olvido-contrasena"){ForgotPasswordScreen1(navController,usrViewModel)}
        composable("olvido-contrasena2"){ForgotPasswordScreen2(navController,usrViewModel)}
        //composable("editar-task"){TaskEditScreen(navController,tskViewModel)}
        composable(
            route = "editar-task/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments!!.getInt("taskId")
            TaskEditScreen(navController, tskViewModel, taskId)
        }


    }
}