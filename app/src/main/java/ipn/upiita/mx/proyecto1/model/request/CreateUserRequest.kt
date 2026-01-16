package ipn.upiita.mx.proyecto1.model.request

data class CreateUserRequest(
    val nombre: String,
    val correo: String,
    val boleta: String,
    val carrera: String,
    val contrasena: String
)
