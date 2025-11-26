@file:OptIn(ExperimentalMaterial3Api::class)
package ipn.upiita.mx.proyecto1.ui

import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import ipn.upiita.mx.proyecto1.viewModel.UserViewModel

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ipn.upiita.mx.proyecto1.viewModel.*
import ipn.upiita.mx.proyecto1.model.*
@Composable

fun UserListScreen(viewModel: UserViewModel, onSearch: () -> Unit) {
    val users by viewModel.users.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadUsers() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User List") },
                actions = { IconButton(onClick = onSearch) { Icon(Icons.Default.Search, null) } }
            )
        }
    ) { padding ->
        LazyColumn(Modifier.padding(padding)) {
            items(users) { user ->
                UserItem(user)
            }
        }
    }

}



@Composable
fun UserItem(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(12.dp)) {
            Text("🆔 ID: ${user.boleta ?: "-"}")
            Text("  ${user.nombre}")
            Text(" carrera : ${user.carrera}")
            Text("correo : ${user.correo}")
        }
    }
}