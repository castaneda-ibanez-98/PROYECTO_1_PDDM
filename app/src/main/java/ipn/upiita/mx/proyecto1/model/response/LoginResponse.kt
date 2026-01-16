package ipn.upiita.mx.proyecto1.model.response

import ipn.upiita.mx.proyecto1.model.DTO.UserDTO

data class LoginResponse(
    val token: String,
    val user: UserDTO? = null
)