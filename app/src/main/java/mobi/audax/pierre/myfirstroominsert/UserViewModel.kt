package mobi.audax.pierre.myfirstroominsert

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import mobi.audax.pierre.myfirstroominsert.db.User
import mobi.audax.pierre.myfirstroominsert.db.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private var isDelete:Boolean = false
    val users = repository.users.asLiveData()
    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()


    fun insertUser(){

        val name = inputName.value!!
        val email = inputEmail.value!!
        insert(User(0, name, email))
        inputName.value = ""
        inputEmail.value = ""
    }

    fun insert(user: User) = viewModelScope.launch(IO) {
        repository.insert(user)
        Log.e("TAG", "insert: ${Thread.currentThread().name}" )
    }
    fun deleteAl() = viewModelScope.launch {
        repository.deleteAll()
        Log.e("TAG", "delete: ${Thread.currentThread().name}" )
    }
    fun initUpdateAndDelete(user: User) = viewModelScope.launch {
        inputName.value = user.name
        inputEmail.value = user.email
        isDelete = true
    }

}