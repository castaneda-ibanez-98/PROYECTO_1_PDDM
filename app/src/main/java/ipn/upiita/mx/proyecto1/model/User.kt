package ipn.upiita.mx.proyecto1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val boleta: String ,
    val nombre: String,
    val correo: String,
    val carrera: String,
    val contrasena: String


)