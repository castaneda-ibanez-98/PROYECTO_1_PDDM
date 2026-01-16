package ipn.upiita.mx.proyecto1.model.DTO

data class SyncResponseDTO(val serverUpdates: List<TaskDTO>,
                           val conflicts: List<ConflictDTO>)
