package mobi.audax.pierre.myfirstroominsert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mobi.audax.pierre.myfirstroominsert.db.UserRepository

class UserViewModelFactory(private val repository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("View model desconhecido")
    }
}