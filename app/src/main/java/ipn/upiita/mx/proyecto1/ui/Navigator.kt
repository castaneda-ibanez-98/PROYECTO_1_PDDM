package ipn.upiita.mx.proyecto1.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ipn.upiita.mx.proyecto1.ui.*

@Composable
fun Navigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { LoginScreen(navController) }
        composable("segunda") { MainMenuScreen(navController) }
        composable("registro") { RegisterScreen(navController) }
    }
}