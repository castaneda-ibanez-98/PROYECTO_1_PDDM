package ipn.upiita.mx.proyecto1.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class ModifyMajorScreenViewModel : ViewModel(){

    var carrera by mutableStateOf("")

    var carreraError by mutableStateOf("")

    fun modifyMajor(){
        print("se ah modificado la carrera a " + carrera)
    }

}