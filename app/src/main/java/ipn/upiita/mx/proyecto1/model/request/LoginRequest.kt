package ipn.upiita.mx.proyecto1.model.request

data class LoginRequest(
    val correo: String,
    val contrasena: String
)