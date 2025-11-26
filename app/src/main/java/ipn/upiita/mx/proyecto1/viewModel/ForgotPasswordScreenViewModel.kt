package ipn.upiita.mx.proyecto1.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import ipn.upiita.mx.proyecto1.model.User
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class ForgotPasswordScreenViewModel( private val userViewModel: UserViewModel): ViewModel() {

    var correo by mutableStateOf("")
    var pregunta by mutableStateOf("")
    var preguntaValida by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    var existeUsuario by mutableStateOf("")
    var existeUsuarioError by mutableStateOf("")
    // Mensajes de error
    var correoError by mutableStateOf("")

    var preguntaError by mutableStateOf("")
    var passwordError by mutableStateOf("")
    var confirmPasswordError by mutableStateOf("")

    var cambioExitoso by mutableStateOf("")

    fun validarCampos(): Boolean {
        var isValid = true

        correoError = if (!isEmailValido(correo)) {
            Log.d("UserRegisterScreenViewModel", "el email no es valido")
            isValid = false
            "Correo no válido"
        } else {
            Log.d("UserRegisterScreenViewModel", "el email es valido")
            ""}

        passwordError = if (isValidPassword(password)) {
            Log.d("UserRegisterScreenViewModel", "la contraseña NO es valida")
            isValid = false
            "La contraseña debe tener al menos 8 caracteres, un numero, una mayuscula y minuscula"
        } else { "" }

        confirmPasswordError = if (confirmPassword != password) {
            Log.d("UserRegisterScreenViewModel", "la contraseña NO es coincide")
            isValid = false
            "Las contraseñas no coinciden"
        } else {
            Log.d("UserRegisterScreenViewModel", "las contraseñas no coinciden")
            "" }


        Log.d("UserRegisterScreenViewModel", "isValid es: "+ isValid)
        return isValid
    }


    fun verificarUsuario(){
        viewModelScope.launch {
            if (userViewModel.getUserByEmail(correo) != null) {
                existeUsuario = "el usuario existe"
            } else {
                existeUsuarioError = "el usuario no existe"
            }
        }
    }

    fun verificarPregunta(){
        viewModelScope.launch {
            Log.d("UserRegisterScreenViewModel", "verificando la pregunta")

            if (userViewModel.getUserByEmail(correo)?.boleta != pregunta)
                {
                    preguntaError = "boleta incorrecta, vuelva a intentar"
                } else {
                    preguntaValida = "pregunta validada"
                }


        }
    }

    fun actualizarUsuario(){
        viewModelScope.launch {

            Log.d("UserRegisterScreenViewModel", "iniciando metodo de actualizacion")
            if (validarCampos()) {

                Log.d(
                    "UserRegisterScreenViewModel",
                    "todos los campos correctos y validados dentro del view model"
                )
                var usuario = userViewModel.getUserByEmail(correo)

                if (usuario != null) {
                    val usuarioActualizado = usuario.copy(contrasena = password)
                    userViewModel.actualizarUsuario(usuarioActualizado)
                }
                Log.d(
                    "UserRegisterScreenViewModel",
                    "procediendo a la insercion en el userViewModel"
                )

                cambioExitoso = "se ah cambiado al la contraseña"
            }
        }
    }

    private fun isEmailValido(email: String): Boolean {
        val pattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        )
        return pattern.matcher(email).matches()
    }
    fun isValidPassword(password: String): Boolean {
        val regex = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}\$")
        return regex.matcher(password).matches()
    }



}
