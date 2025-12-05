@file:OptIn(ExperimentalMaterial3Api::class)
package ipn.upiita.mx.proyecto1.ui

import android.R
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ipn.upiita.mx.proyecto1.viewModel.LoginScreenViewModel
import ipn.upiita.mx.proyecto1.viewModel.UserViewModel
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.Color
import ipn.upiita.mx.proyecto1.viewModel.Sesion

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController, userViewModel: UserViewModel,sesion: Sesion) {
val LSviewModel = remember { LoginScreenViewModel(userViewModel, sesion ) }


    fun goFormReg(){
        navController.navigate("registro")
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
                value = LSviewModel.email,
                onValueChange = { LSviewModel.email = it },
                label = { Text("Correo electrónico") },
                isError = LSviewModel.emailError.isNotEmpty(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (LSviewModel.emailError.isNotEmpty()) {
                Text(LSviewModel.emailError, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(8.dp))



            OutlinedTextField(
                value = LSviewModel.password,
                onValueChange = { LSviewModel.password = it },
                label = { Text("Contraseña") },
                isError = LSviewModel.passwordError.isNotEmpty(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (LSviewModel.passwordError.isNotEmpty()) {
                Text(LSviewModel.passwordError, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "olvido su contraseña?",
                modifier = Modifier.clickable {
                    navController.navigate("olvido-contrasena")
                }.padding(8.dp),
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )

            Button(
                onClick = {
                          LSviewModel.validateAndLogin(
                              onSuccess ={ navController.navigate("main_menu")},
                              onError = {mensaje->Log.d("login","error al iniciar sesion")}
                          )
                          },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ingresar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { goFormReg() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("registrarse")
            }

            LSviewModel.loginError?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = it,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
                )
            }



        }
    }
}