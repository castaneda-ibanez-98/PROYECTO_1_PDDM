package ipn.upiita.mx.proyecto1.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "task",
     indices = [Index("userId")]
)
data class Task(
    @PrimaryKey val id: Int,
    val userId:Int,
    val name: String,
    val deadline:String,
    val status:String,
    val updatedAt: Long,
    val pendingSync: Boolean = false,
    val deleted: Boolean = false

    
)
