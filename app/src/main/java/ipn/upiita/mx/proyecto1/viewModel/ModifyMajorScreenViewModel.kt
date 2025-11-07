package ipn.upiita.mx.proyecto1.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class ModifyMajorScreenViewModel : ViewModel(){

    var carrera by mutableStateOf("")

    var carreraModificada by mutableStateOf("")
    var carreraError by mutableStateOf("")

    fun modifyMajor(){
        carreraModificada = "la carrera se a modificado a "+ carrera
    }

}