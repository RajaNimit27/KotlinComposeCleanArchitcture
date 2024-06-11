package data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_post")
data class PostEntity(
    @PrimaryKey
    var id: Int? = null,
    var body: String? = null,
    var title: String? = null,
    var userId: Int? = null
)