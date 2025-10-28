package ipn.upiita.mx.proyecto1.ui

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ipn.upiita.mx.proyecto1.ui.*
import ipn.upiita.mx.proyecto1.viewModel.UserRegisterScreenViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ipn.upiita.mx.proyecto1.viewModel.LoginScreenViewModel
import ipn.upiita.mx.proyecto1.viewModel.ModifyMajorScreenViewModel

@Preview
@Composable
fun Navigator() {
    val navController = rememberNavController()
    val UsrRgstVM: UserRegisterScreenViewModel = viewModel()
    val LoginScreenVM: LoginScreenViewModel= viewModel()
    val mdfyMjrVM: ModifyMajorScreenViewModel = viewModel()
    NavHost(navController = navController, startDestination = "main_menu") {
        composable("inicio") { LoginScreen(navController,LoginScreenVM) }
        composable("main_menu") { MainMenuScreen(navController) }
        composable("registro") { RegisterScreen(navController,UsrRgstVM) }
        composable("cambio_carrera"){ModifyMajorScreen(navController,mdfyMjrVM)}
    }
}