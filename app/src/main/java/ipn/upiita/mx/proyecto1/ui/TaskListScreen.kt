@file:OptIn(ExperimentalMaterial3Api::class)
package ipn.upiita.mx.proyecto1.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.Modifier
import ipn.upiita.mx.proyecto1.viewModel.TaskViewModel
import ipn.upiita.mx.proyecto1.viewModel.UserViewModel
import androidx.lifecycle.ViewModel
@Composable
fun TaskListScreen(viewModel: TaskViewModel,onSearch: () -> Unit){
        val tasks by viewModel.tasks.collectAsState()

        LaunchedEffect(Unit) { viewModel.loadAllTasks() }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("User List") },
                    actions = { IconButton(onClick = onSearch) { Icon(Icons.Default.Search, null) } }
                )
            }
        ) { padding ->
            LazyColumn(Modifier.padding(padding)) {
                items(tasks) { task ->
                    ListItem(
                        headlineContent = { Text(task.name) },
                        supportingContent = { Text(task.status) }
                    )
                }
            }
        }
    }
