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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ModifyMajorScreen(navController: NavHostController,
                      MMVM: ModifyMajorScreenViewModel= viewModel()) {


    fun registrar(){
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



            OutlinedTextField(
                value = MMVM.carrera,
                onValueChange = { MMVM.carrera = it },
                label = { Text("carrera") },
                isError = MMVM.carreraError.isNotEmpty(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (MMVM.carreraError.isNotEmpty()) {
                Text(MMVM.carreraError, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))
            if (MMVM.carreraModificada.isNotEmpty()) {
                Text(MMVM.carreraModificada, color = MaterialTheme.colorScheme.error)
            }
            Button(
                onClick = { registrar() },
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