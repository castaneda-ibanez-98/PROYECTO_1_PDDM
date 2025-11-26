package ipn.upiita.mx.proyecto1.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ipn.upiita.mx.proyecto1.model.TaskRepository
import ipn.upiita.mx.proyecto1.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository): ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> get() = _tasks

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> get() = _selectedTask

    fun loadAllTasks() {
        viewModelScope.launch {
            _tasks.value = repository.getAll()
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            Log.d("UserViewModel", "Intentando insertar usuario: ${task.id}")
            repository.insert(task)
            Log.d("UserViewModel", "Inserción completada.")
        }
    }

    suspend fun getTaskByName(email:String):Task?{
        return repository.findByName(email)
    }



    fun findTaskByName(name: String) {
        viewModelScope.launch {
            _selectedTask.value = repository.findByName(name)
        }
    }

    fun findTaskById(taskId: Int) {
        viewModelScope.launch {
            _selectedTask.value = repository.findByTaskId(taskId)
        }
    }
}