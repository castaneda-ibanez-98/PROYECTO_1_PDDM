package ipn.upiita.mx.proyecto1.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ipn.upiita.mx.proyecto1.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Sesion : ViewModel() {
    private val _userActual= MutableStateFlow<User?>(null)
    val userActual = _userActual.asStateFlow()

    private val _token = MutableStateFlow<String?>(null)
    val token = _token.asStateFlow()

    fun login(user: User?,token:String){
        _userActual.value = user
        _token.value = token
    }

    fun actualizar(user: User){
        _userActual.value=user
    }

    fun logout(){
        _userActual.value =null
        _token.value=null
    }

}