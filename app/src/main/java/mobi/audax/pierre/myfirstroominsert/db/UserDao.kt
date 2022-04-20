package mobi.audax.pierre.myfirstroominsert.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user:User)

    @Update
    suspend fun updateUser(user:User)

    @Query("DELETE FROM user_Table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM user_Table")
    fun getAllUsers(): Flow<List<User>>
}