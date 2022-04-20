package mobi.audax.pierre.myfirstroominsert

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import mobi.audax.pierre.myfirstroominsert.databinding.ListItemBinding
import mobi.audax.pierre.myfirstroominsert.db.User

class MyRecyclerViewAdapter(
    private val clickListener: (User) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {

    private val userList = ArrayList<User>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setList(users: List<User>) {
        userList.clear()
        userList.addAll(users)
    }


}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User, clickListener: (User) -> Unit) {
        binding.nameTextView.text = user.name
        binding.emailTextView.text = user.email
        binding.listItemLayout.setOnClickListener {
            clickListener(user)
        }
    }
}