@file:OptIn(ExperimentalMaterial3Api::class)
package ipn.upiita.mx.proyecto1.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ipn.upiita.mx.proyecto1.viewModel.LoginScreenViewModel
import ipn.upiita.mx.proyecto1.viewModel.ModifyMajorScreenViewModel
import ipn.upiita.mx.proyecto1.viewModel.Sesion


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ModifyMajorScreen(navController: NavHostController,
                      MMVM: ModifyMajorScreenViewModel= viewModel(),sesion: Sesion) {

    val user by sesion.userActual.collectAsState()

    fun cambiar(){
        MMVM.modifyMajor()
    }
    fun goBack(){
        navController.navigate("main_menu")
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Iniciar Sesión") })
        }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            /*<><><><><>*/
            ExposedDropdownMenuBox(
                expanded = MMVM.expanded,
                onExpandedChange = { MMVM.expanded = !MMVM.expanded }
            ) {
                OutlinedTextField(
                    value = MMVM.carrera,
                    onValueChange = {},
                    label = { Text("Carrera") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = MMVM.expanded)
                    },
                    readOnly = true,   // IMPORTANTE
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = MMVM.expanded,
                    onDismissRequest = { MMVM.expanded = false }
                ) {
                    MMVM.opciones.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                MMVM.carrera = opcion
                                MMVM.expanded = false
                            }
                        )
                    }
                }
            }
            /*<><><><><><><><><><><><><><><><>*/
            Spacer(modifier = Modifier.height(16.dp))
            if (MMVM.carreraModificada.isNotEmpty()) {
                Text(MMVM.carreraModificada, color = MaterialTheme.colorScheme.error)
            }
            Button(
                onClick = { cambiar() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Modificar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { goBack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }



        }
    }
}