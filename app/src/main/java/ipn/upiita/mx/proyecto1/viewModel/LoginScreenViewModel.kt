package ipn.upiita.mx.proyecto1.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.util.regex.Pattern
import ipn.upiita.mx.proyecto1.ui.*
import ipn.upiita.mx.proyecto1.ui.Navigator
import androidx.navigation.NavHostController
import androidx.lifecycle.ViewModel
class LoginScreenViewModel(private val userViewModel: UserViewModel) : ViewModel(){
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    // Mensajes de error
    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")


    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        val regex = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}\$")
        return regex.matcher(password).matches()
    }

    fun validate():Boolean{
        var isValid= false
        emailError = if (isValidEmail(email)) "" else "Email no válido"
        passwordError = if (isValidPassword(password)) "" else "Contraseña inválida";

        if(emailError.isEmpty() && passwordError.isEmpty()){
            isValid=true;
        }

        return isValid;
    }
}




