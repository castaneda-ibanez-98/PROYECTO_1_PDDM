@file:OptIn(ExperimentalMaterial3Api::class)
package ipn.upiita.mx.proyecto1.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ipn.upiita.mx.proyecto1.viewModel.TaskViewModel
import ipn.upiita.mx.proyecto1.viewModel.UserViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import ipn.upiita.mx.proyecto1.model.*

@Composable
fun TaskListScreen(navController: NavHostController  ,viewModel: TaskViewModel, onSearch: () -> Unit){
        val tasks by viewModel.tasks.collectAsState()

        LaunchedEffect(Unit) { viewModel.loadAllTasks() }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("lista de tareas") },
                    actions = { IconButton(onClick = onSearch) { Icon(Icons.Default.Search, null) } }
                )
            }
        ) { padding ->
            LazyColumn(Modifier.padding(padding)) {
                items(tasks) { task ->
                    TaskItem(task = task,
                        onDelete = { viewModel.eliminarTask(task) },
                        onEdit = { navController.navigate("editar-task")})
                }
            }
        }
    }
@Composable
fun TaskItem(task: Task,
             onDelete:(Task)->Unit = {},
             onEdit:(Task)->Unit={}
) {
    var expandido by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.padding(12.dp)) {
                Text("🆔 ID: ${task.id ?: "-"}")
                Text("📌 ${task.name}")
                Text("✅ Estado: ${task.status}")
                Text("📅 Deadline: ${task.deadline}")
            }
            Box {
                IconButton(onClick = { expandido = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "opciones")
                }
            }
            DropdownMenu( expanded = expandido,
                onDismissRequest = {expandido=false}) {
                DropdownMenuItem(text = {Text("editar")},
                    onClick = {expandido =false
                    onEdit(task)})
                DropdownMenuItem(text = {Text("eliminar")},
                    onClick = {expandido = false
                        onDelete(task)
                    })

            }


        }
    }
}
