package com.metinvandar.usersandposts.presentation.userposts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.metinvandar.usersandposts.databinding.PostItemBinding
import com.metinvandar.usersandposts.domain.model.UserPost

class PostsAdapter(private val posts: List<UserPost>) :
    RecyclerView.Adapter<PostItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        val binding =
            PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostItemViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size
}
