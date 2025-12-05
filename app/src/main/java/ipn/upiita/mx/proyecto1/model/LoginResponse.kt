package ipn.upiita.mx.proyecto1.model



data class LoginResponse(
    val token: String,
    val user: User? = null
)
