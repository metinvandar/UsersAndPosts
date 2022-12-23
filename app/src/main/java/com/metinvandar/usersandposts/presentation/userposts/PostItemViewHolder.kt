package com.metinvandar.usersandposts.presentation.userposts

import androidx.recyclerview.widget.RecyclerView
import com.metinvandar.usersandposts.databinding.PostItemBinding
import com.metinvandar.usersandposts.domain.model.UserPost

class PostItemViewHolder(private val postItemBinding: PostItemBinding): RecyclerView.ViewHolder(postItemBinding.root) {

    fun bind(post: UserPost) {
        postItemBinding.run {
            postTitle.text = post.title
            postBody.text = post.body
        }
    }
}
