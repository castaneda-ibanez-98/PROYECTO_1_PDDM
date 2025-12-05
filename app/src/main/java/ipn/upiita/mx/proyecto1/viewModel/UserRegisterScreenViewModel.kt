package ipn.upiita.mx.proyecto1.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.regex.Pattern
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ipn.upiita.mx.proyecto1.model.*
import kotlinx.coroutines.launch
import android.util.Log
class UserRegisterScreenViewModel(private val userViewModel: UserViewModel) {

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
    var mensajeRegistroExitoso by mutableStateOf("")



    var expanded by mutableStateOf(false)
    val opciones = listOf<String>("Ingenieria Telematica",
        "Ingenieria Mecatronica",
        "Ingenieria en Energia",
        "Ingenieria Bionicia")

    fun validarCampos(): Boolean {
        var isValid = true
        Log.d("UserRegisterScreenViewModel", "iniciando comprobacion de campos")
        nombreError = if (nombre.isBlank()) {
            Log.d("UserRegisterScreenViewModel", "el nombre no es valido")
            isValid = false
            "El nombre es obligatorio"
        } else {
            Log.d("UserRegisterScreenViewModel", "el nombre esta llenado")
            ""
        }

        boletaError = if (boleta.isBlank()) {
            Log.d("UserRegisterScreenViewModel", "la boleta no es valida")
            isValid = false
            "La boleta es obligatoria"
        } else {""}

        correoError = if (!isEmailValido(correo)) {
            Log.d("UserRegisterScreenViewModel", "el email no es valido")
            isValid = false
            "Correo no válido"
        } else {
            Log.d("UserRegisterScreenViewModel", "el email es valido")
            ""}

        carreraError = if (carrera.isBlank()) {
            Log.d("UserRegisterScreenViewModel", "la carrera no es valido")
                    isValid = false
            "La carrera es obligatoria"
        } else {
            Log.d("UserRegisterScreenViewModel", "la carrera es valida")
            ""}

        passwordError = if (!isValidPassword(password)) {
            Log.d("UserRegisterScreenViewModel", "la contraseña NO es valida")
            isValid = false
            "La contraseña debe tener al menos 8 caracteres, un numero, una mayuscula y minuscula"
        } else { "" }

        confirmPasswordError = if (confirmPassword != password) {
            Log.d("UserRegisterScreenViewModel", "la contraseña NO  coinciden")
            isValid = false
            "Las contraseñas no coinciden"
        } else {
            Log.d("UserRegisterScreenViewModel", "las contraseñas  coinciden")
            "" }

        registroExitoso = isValid
        Log.d("UserRegisterScreenViewModel", "isValid es: "+isValid)
        return isValid
    }


    fun registrarUsuario(onSuccess: () -> Unit, onError: (String) -> Unit){
        Log.d("UserRegisterScreenViewModel", "iniciando metodo de insercion")
        if(validarCampos()) {
            Log.d("UserRegisterScreenViewModel", "todos los campos correctos y validados dentro del view model")
            val usuario = User(
                boleta = boleta,
                nombre = nombre,
                carrera = carrera,
                contrasena = password,
                correo = correo
            )
            Log.d("UserRegisterScreenViewModel", "procediendo a la insercion en el userViewModel")
            userViewModel.addUser(usuario )
            onSuccess()
            registroExitoso = true
            mensajeRegistroExitoso = "se ah registrado al usuario"
        }else
        { onError("usuario no encontrado") }

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