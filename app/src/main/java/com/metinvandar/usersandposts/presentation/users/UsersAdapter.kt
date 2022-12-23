package com.metinvandar.usersandposts.presentation.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.metinvandar.usersandposts.databinding.UserRowItemBinding
import com.metinvandar.usersandposts.domain.model.User

class UsersAdapter(private val users: List<User>) : RecyclerView.Adapter<UserItemViewHolder>() {

    var itemClick: ((user: User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val binding = UserRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(users[position], itemClick)
    }

    override fun getItemCount(): Int {
        return users.size
    }
}
