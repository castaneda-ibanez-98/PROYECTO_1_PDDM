package ipn.upiita.mx.proyecto1.model.request

data class UpdateTaskRequest(val id : Int,
                             val name:String,
                             val deadline: String,
                             val status: String)
