package ipn.upiita.mx.proyecto1.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ipn.upiita.mx.proyecto1.model.CrearTask
import ipn.upiita.mx.proyecto1.model.Task
import kotlin.Int

class TaskRegisterScreenViewModel(private val taskViewModel: TaskViewModel) {
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
        Log.d("TaskRegisterScreenViewModel", "iniciando comprobacion de campos")
        nombreError = if (nombre.isBlank()) {
            Log.d("TaskRegisterScreenViewModel", "el nombre no es valido")
            isValid = false
            "El nombre es obligatorio"
        } else {
            Log.d("TaskRegisterScreenViewModel", "el nombre esta llenado")
            ""
        }
        fechaError = if (fecha.isBlank()) {
            Log.d("TaskRegisterScreenViewModel", "la fecha no es valida")
            isValid = false
            "La fecha es obligatoria"
        } else {""}

        estadoError = if (estado.isBlank()) {
            Log.d("TaskRegisterScreenViewModel", "el status no es valido")
            isValid = false
            "estado es obligatorio"
        } else {""}


        registroExitoso = isValid
        Log.d("TaskRegisterScreenViewModel", "isValid es: "+isValid)
        return isValid
    }


    fun insertarTarea(){
        Log.d("TaskRegisterScreenViewModel", "iniciando metodo de insercion")
        if(validarCampos()) {
            Log.d("TaskRegisterScreenViewModel", "todos los campos correctos y validados dentro del view model")
            val tarea = CrearTask(
                name = nombre,
                deadline = fecha,
                status = estado,
            )
            Log.d("TaskRegisterScreenViewModel", "procediendo a la insercion en el taskViewModel")

            taskViewModel.addTask(tarea)
            registroExitoso = true
            mensajeRegistroExitoso = "se ah registrado la tarea"
        }
    }






}