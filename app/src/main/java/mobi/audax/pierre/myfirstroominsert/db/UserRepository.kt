package mobi.audax.pierre.myfirstroominsert.db

class UserRepository(private val dao: UserDao) {
    val users = dao.getAllUsers()

    suspend fun insert(user: User) {
        return dao.insertUser(user)
    }

    suspend fun update(user: User) {
        return dao.updateUser(user)
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }

}