package ipn.upiita.mx.proyecto1.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.regex.Pattern
import androidx.lifecycle.ViewModel

class UserRegisterScreenViewModel : ViewModel() {
    var nombre by mutableStateOf("")
    var boleta by mutableStateOf("")
    var correo by mutableStateOf("")
    var carrera by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    // Mensajes de error
    var nombreError by mutableStateOf("")
    var boletaError by mutableStateOf("")
    var correoError by mutableStateOf("")
    var carreraError by mutableStateOf("")
    var passwordError by mutableStateOf("")
    var confirmPasswordError by mutableStateOf("")
    var registroExitoso by mutableStateOf(false)

    fun validarCampos(): Boolean {
        var isValid = true

        nombreError = if (nombre.isBlank()) {
            isValid = false
            "El nombre es obligatorio"
        } else {""}

        boletaError = if (boleta.isBlank()) {
            isValid = false
            "La boleta es obligatoria"
        } else {""}

        correoError = if (!isEmailValido(correo)) {
            isValid = false
            "Correo no válido"
        } else {""}

        carreraError = if (carrera.isBlank()) {
            isValid = false
            "La carrera es obligatoria"
        } else {""}

        passwordError = if (password.length < 6) {
            isValid = false
            "La contraseña debe tener al menos 6 caracteres"
        } else { "" }

        confirmPasswordError = if (confirmPassword != password) {
            isValid = false
            "Las contraseñas no coinciden"
        } else {""}

        registroExitoso = isValid
        return isValid
    }

    private fun isEmailValido(email: String): Boolean {
        val pattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        )
        return pattern.matcher(email).matches()
    }

}