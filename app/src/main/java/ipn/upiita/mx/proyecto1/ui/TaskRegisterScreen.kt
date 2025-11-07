@file:OptIn(ExperimentalMaterial3Api::class)
package ipn.upiita.mx.proyecto1.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import android.app.DatePickerDialog
import androidx.compose.material3.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import androidx.navigation.NavHostController
import ipn.upiita.mx.proyecto1.viewModel.UserRegisterScreenViewModel
import ipn.upiita.mx.proyecto1.viewModel.UserViewModel
import ipn.upiita.mx.proyecto1.viewModel.TaskRegisterScreenViewModel
import ipn.upiita.mx.proyecto1.viewModel.TaskViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskRegisterScreen (navController: NavHostController,taskViewModel: TaskViewModel) {
    val regVM = remember { TaskRegisterScreenViewModel(taskViewModel) }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Registro de Tarea") })
        }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /*zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz*/
            /*cambios hchos por mi*/
            OutlinedTextField(
                value = regVM.nombre,
                onValueChange = { regVM.nombre = it },
                label = { Text("nombre") },
                isError = regVM.nombreError.isNotEmpty() ,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (regVM.nombreError.isNotEmpty()) {
                Text(regVM.nombreError, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(8.dp))

            /*zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz*/


            DatePickerField(
                fecha = regVM.fecha,
                onFechaSeleccionada = { regVM.fecha = it }
            )
            if (regVM.fechaError.isNotEmpty()) {
                Text(regVM.fechaError, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(8.dp))

            /*zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz*/
            /*cambios hchos por mi*/
            OutlinedTextField(
                value = regVM.estado,
                onValueChange = { regVM.estado = it },
                label = { Text("status") },
                isError = regVM.estadoError.isNotEmpty(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number ,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (regVM.estado.isNotEmpty()) {
                Text(regVM.estadoError, color = MaterialTheme.colorScheme.error)
            }
            Spacer(modifier = Modifier.height(8.dp))
            /*zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz*/

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    Log.d("RegisterScreen", "Botón presionado")
                    if(regVM.validarCampos()) {
                        regVM.insertarTarea()
                        navController.navigate("main_menu")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar")
            }
        }
    }
}

@Composable
fun DatePickerField(
    fecha: String,
    onFechaSeleccionada: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, y, m, d ->
            val fechaSeleccionada = String.format("%04d-%02d-%02d", y, m + 1, d)
            onFechaSeleccionada(fechaSeleccionada)
        },
        year,
        month,
        day
    )

    OutlinedTextField(
        value = fecha,
        onValueChange = {},
        label = { Text("Fecha de entrega") },
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { datePickerDialog.show() }) {
                Icon(Icons.Default.DateRange, contentDescription = "Seleccionar fecha")
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}
