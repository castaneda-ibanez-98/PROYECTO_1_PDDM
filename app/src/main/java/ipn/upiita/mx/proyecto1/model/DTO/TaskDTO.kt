package ipn.upiita.mx.proyecto1.model.DTO

data class TaskDTO(val id: Int,
                   val userId: Int,
                   val name: String,
                   val status: String,
                   val deadline: String,
                   val updatedAt: Long,
                   val deleted: Boolean,
                   val tempId: Int? = null/*campo agregado para saber que eliminar*/
)
