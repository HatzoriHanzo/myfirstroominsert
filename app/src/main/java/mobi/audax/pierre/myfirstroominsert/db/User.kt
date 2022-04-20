package mobi.audax.pierre.myfirstroominsert.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_Table")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var email: String
)
