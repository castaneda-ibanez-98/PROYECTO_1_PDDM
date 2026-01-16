package ipn.upiita.mx.proyecto1.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ipn.upiita.mx.proyecto1.model.*
import android.util.Log
import ipn.upiita.mx.proyecto1.model.DTO.UserDTO
import ipn.upiita.mx.proyecto1.model.repositorios.UserRepository
import ipn.upiita.mx.proyecto1.model.request.CreateUserRequest
import kotlinx.coroutines.flow.mapNotNull

class UserViewModel (private val repository: UserRepository) : ViewModel() {

    private val _users = MutableStateFlow<List<UserDTO>>(emptyList())
    val users: StateFlow<List<UserDTO>> get() = _users

    private val _selectedUser = MutableStateFlow<UserDTO?>(null)
    val selectedUser: StateFlow<UserDTO?> get() = _selectedUser

    fun loadUsers() {
        viewModelScope.launch {
            _users.value = repository.getAll()
        }
    }

    fun addUser(user: CreateUserRequest) {
        viewModelScope.launch {
            Log.d("UserViewModel", "Intentando insertar usuario: ${user.nombre}")
            repository.insert(user)
            Log.d("UserViewModel", "Inserción completada.")
        }
    }

    suspend fun getUserByEmail(email:String):UserDTO?{
        return repository.findByEmail(email)
    }


    fun findUserByEmail(email: String) {
        viewModelScope.launch {
            _selectedUser.value = repository.findByEmail(email)
        }
    }


    fun findUserByStudentId(studentId: Int) {
        viewModelScope.launch {
            _selectedUser.value = repository.findByStudentId(studentId)
        }
    }
    fun actualizarUsuario(user: UserDTO?){
        viewModelScope.launch {
            repository.update(user)
        }
    }
    suspend fun login(email:String,password:String){
         repository.Login(email,password)
    }
    suspend fun obtenerActualizado(user: UserDTO){
        viewModelScope.launch {
            repository.obtenerActualizado(user)
        }
    }

}