package ipn.upiita.mx.proyecto1.model.sync

import ipn.upiita.mx.proyecto1.model.Task
import ipn.upiita.mx.proyecto1.model.DTO.TaskDTO

fun Task.toDTO()= TaskDTO(
    id,
    userId,
    name,
    status,
    deadline,
    updatedAt,
    deleted)
fun TaskDTO.toEntity() = Task(
    id = id,
    userId = userId,
    name = name,
    deadline = deadline,
    status = status,
    updatedAt = updatedAt,
    pendingSync = false,
    deleted = false
)

