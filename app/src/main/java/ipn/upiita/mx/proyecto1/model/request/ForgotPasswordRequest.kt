package ipn.upiita.mx.proyecto1.model.request

data class ForgotPasswordRequest(
    val boleta :String,
    val newPassword: String
)
