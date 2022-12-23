package com.metinvandar.usersandposts.presentation.users

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metinvandar.usersandposts.databinding.UserRowItemBinding
import com.metinvandar.usersandposts.domain.model.User

class UserItemViewHolder(private val binding: UserRowItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, itemClick: ((user: User) -> Unit)?) {
        binding.run {
            userName.text = user.name
            postCount.text = user.postCount.toString()
            Glide.with(itemView.context).load(user.image).into(userImage)
            root.setOnClickListener { itemClick?.invoke(user) }
        }
    }
}
