package ipn.upiita.mx.proyecto1.model.request

data class UpdatePasswordRequest(val currentPassword: String,
                                 val newPassword: String)
