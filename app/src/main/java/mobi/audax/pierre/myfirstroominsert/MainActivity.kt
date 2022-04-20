package mobi.audax.pierre.myfirstroominsert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import mobi.audax.pierre.myfirstroominsert.databinding.ActivityMainBinding
import mobi.audax.pierre.myfirstroominsert.db.User
import mobi.audax.pierre.myfirstroominsert.db.UserDao
import mobi.audax.pierre.myfirstroominsert.db.UserDatabase
import mobi.audax.pierre.myfirstroominsert.db.UserRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: MyRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        val dao: UserDao = UserDatabase.getInstance(application).dao
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        binding.myViewModel = userViewModel

        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyRecyclerViewAdapter({ selectedItem: User -> listItemClicked(selectedItem) })
        binding.recyclerView.adapter = adapter

        observeUsers()
    }

    private fun observeUsers() {
        userViewModel.users.observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(user: User) {
        Toast.makeText(this, "name is : ${user.name}", Toast.LENGTH_SHORT).show()
        userViewModel.initUpdateAndDelete(user)

    }
}