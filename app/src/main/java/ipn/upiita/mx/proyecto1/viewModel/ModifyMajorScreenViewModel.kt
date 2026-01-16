package ipn.upiita.mx.proyecto1.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ipn.upiita.mx.proyecto1.model.DTO.UserDTO
import ipn.upiita.mx.proyecto1.model.User
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ModifyMajorScreenViewModel(
    private  val userViewModel: UserViewModel,
    private val sesion: Sesion): ViewModel(){


    var expanded by mutableStateOf(false)
    val opciones = listOf<String>("Ingenieria Telematica",
        "Ingenieria Mecatronica",
        "Ingenieria en Energia",
        "Ingenieria Bionicia")
    var carrera by mutableStateOf("")

    var carreraModificada by mutableStateOf("")
    var carreraError by mutableStateOf("")

    fun modifyMajor() {
        viewModelScope.launch {
            val actual = sesion.userActual.value
            if (actual != null) {
                var actualizado = UserDTO(
                    id=actual.id,
                    nombre = actual.nombre,
                    correo = actual.correo,
                    boleta = actual.boleta,
                    carrera = carrera
                )
                userViewModel.actualizarUsuario(actualizado)
                    sesion.actualizar(actualizado)
            }
            carreraModificada = "la carrera se a modificado a " + carrera
        }
    }
}