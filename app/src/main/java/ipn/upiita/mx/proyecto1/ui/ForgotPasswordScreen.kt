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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ipn.upiita.mx.proyecto1.viewModel.ForgotPasswordScreenViewModel
import ipn.upiita.mx.proyecto1.viewModel.LoginScreenViewModel
import ipn.upiita.mx.proyecto1.viewModel.UserRegisterScreenViewModel
import ipn.upiita.mx.proyecto1.viewModel.UserViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForgotPasswordScreen1(navController: NavHostController, userViewModel: UserViewModel) {
    val regVM = remember { ForgotPasswordScreenViewModel(userViewModel) }



    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Olvide mi contraseña") })
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
                value = regVM.correo,
                onValueChange = { regVM.correo = it },
                label = { Text("Correo electrónico") },
                isError = regVM.correoError.isNotEmpty(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (regVM.correoError.isNotEmpty()) {
                Text(regVM.correoError, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { regVM.verificarUsuario() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ingresar correo")
            }

            regVM.existeUsuarioError.let {
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = regVM.existeUsuarioError,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            regVM.existeUsuario.let {
                Text(
                    text = "cual es su boleta",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
                OutlinedTextField(
                    value = regVM.pregunta,
                    onValueChange = { regVM.pregunta = it },
                    label = { Text("respuesta") },
                    isError = regVM.preguntaError.isNotEmpty(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                if (regVM.pregunta.isNotEmpty()) {
                    Text(regVM.pregunta, color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate("inicio") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("cancelar")
                }
                Button(
                    onClick = { navController.navigate("olvido-contrasena2") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("cancelar")
                }

            }





        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForgotPasswordScreen2(navController: NavHostController, userViewModel: UserViewModel) {
    val regVM = remember { UserRegisterScreenViewModel(userViewModel) }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Olvide mi contraseña") })
        }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = regVM.password,
                onValueChange = { regVM.password = it },
                label = { Text("Contraseña") },
                isError = regVM.passwordError.isNotEmpty(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (regVM.passwordError.isNotEmpty()) {
                Text(regVM.passwordError, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = regVM.confirmPassword,
                onValueChange = { regVM.confirmPassword = it },
                label = { Text("confirme la ontraseña") },
                isError = regVM.confirmPasswordError.isNotEmpty(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
            if (regVM.confirmPasswordError.isNotEmpty()) {
                Text(regVM.confirmPasswordError, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {  },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ingresar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {  },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("registrarse")
            }





        }
    }
}