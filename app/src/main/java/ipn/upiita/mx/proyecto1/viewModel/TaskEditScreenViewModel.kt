package ipn.upiita.mx.proyecto1.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ipn.upiita.mx.proyecto1.model.Task

class TaskEditScreenViewModel (private val taskViewModel: TaskViewModel,private val taskId:Int) {
    var nombre by mutableStateOf("")
    var fecha by mutableStateOf("")
    var estado by mutableStateOf("")

    var nombreError by mutableStateOf("")
    var fechaError by mutableStateOf("")
    var estadoError by mutableStateOf("")

    var registroExitoso by mutableStateOf(false)
    var mensajeRegistroExitoso by mutableStateOf("")


    var expanded by mutableStateOf(false)
    val opciones = listOf<String>("Activa",
        "Pausada",
        "Terminada",
        "Pendiente")


    fun validarCampos(): Boolean {
        var isValid = true
        Log.d("TaskEditScreenViewModel", "iniciando comprobacion de campos")
        nombreError = if (nombre.isBlank()) {
            Log.d("TaskEditScreenViewModel", "el nombre no es valido")
            isValid = false
            "El nombre es obligatorio"
        } else {
            Log.d("TaskEditScreenViewModel", "el nombre esta llenado")
            ""
        }
        fechaError = if (fecha.isBlank()) {
            Log.d("TaskEditScreenViewModel", "la fecha no es valida")
            isValid = false
            "La fecha es obligatoria"
        } else {""}

        estadoError = if (estado.isBlank()) {
            Log.d("TaskEditScreenViewModel", "el status no es valido")
            isValid = false
            "estado es obligatorio"
        } else {""}


        registroExitoso = isValid
        Log.d("TaskEditScreenViewModel", "isValid es: "+isValid)
        return isValid
    }


    fun actualizarTarea(){
        Log.d("TaskEditScreenViewModel", "iniciando metodo de insercion actualizacion")
        if(validarCampos()) {
            Log.d("TaskEditScreenViewModel", "todos los campos correctos y validados dentro del view model")
            val editado = Task(
                id = taskId,
                name = nombre,
                deadline = fecha,
                status = estado
            )
            Log.d("TaskEditScreenViewModel", "procediendo a la insercion en el taskViewModel de la task con id = ${editado.id}")

            taskViewModel.updateTask(editado)
            registroExitoso = true
            mensajeRegistroExitoso = "se ah registrado la tarea"
        }
    }






}