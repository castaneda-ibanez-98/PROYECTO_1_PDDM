@file:OptIn(ExperimentalMaterial3Api::class)
package ipn.upiita.mx.proyecto1.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ipn.upiita.mx.proyecto1.viewModel.TaskEditScreenViewModel
import ipn.upiita.mx.proyecto1.viewModel.TaskRegisterScreenViewModel

import ipn.upiita.mx.proyecto1.viewModel.TaskViewModel
import java.util.Calendar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskEditScreen (navController: NavHostController,taskViewModel: TaskViewModel,taskId:Int) {
    val tskVM = remember { TaskEditScreenViewModel(taskViewModel, taskId) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Modificar Tarea") })
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
                value = tskVM.nombre,
                onValueChange = { tskVM.nombre = it },
                label = { Text("nombre") },
                isError = tskVM.nombreError.isNotEmpty() ,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (tskVM.nombreError.isNotEmpty()) {
                Text(tskVM.nombreError, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(8.dp))

            /*zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz*/


            DatePickerFieldEdit(
                fecha = tskVM.fecha,
                onFechaSeleccionada = { tskVM.fecha = it }
            )
            if (tskVM.fechaError.isNotEmpty()) {
                Text(tskVM.fechaError, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(8.dp))

            /*zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz*/
            /*cambios hchos por mi*/
            /*<><><><><>*/
            ExposedDropdownMenuBox(
                expanded = tskVM.expanded,
                onExpandedChange = { tskVM.expanded = !tskVM.expanded }
            ) {
                OutlinedTextField(
                    value = tskVM.estado,
                    onValueChange = {},
                    label = { Text("Carrera") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = tskVM.expanded)
                    },
                    readOnly = true,   // IMPORTANTE
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = tskVM.expanded,
                    onDismissRequest = { tskVM.expanded = false }
                ) {
                    tskVM.opciones.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                tskVM.estado = opcion
                                tskVM.expanded = false
                            }
                        )
                    }
                }
            }
            if (tskVM.estado.isNotEmpty()) {
                Text(tskVM.estadoError, color = MaterialTheme.colorScheme.error)
            }
            Spacer(modifier = Modifier.height(8.dp))
            /*zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz*/

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    Log.d("RegisterScreen", "Botón presionado")
                    if(tskVM.validarCampos()) {
                        tskVM.actualizarTarea()
                        navController.navigate("TaskListScreen")
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
fun DatePickerFieldEdit(
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