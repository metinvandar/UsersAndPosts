package com.metinvandar.usersandposts.presentation.userposts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.metinvandar.usersandposts.R
import com.metinvandar.usersandposts.databinding.FragmentUserPostsBinding
import com.metinvandar.usersandposts.domain.model.User
import com.metinvandar.usersandposts.utils.Constants
import com.metinvandar.usersandposts.utils.VerticalBottomSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserPostsFragment : Fragment(R.layout.fragment_user_posts) {
    private var _binding: FragmentUserPostsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserPostsBinding.bind(view)
        val user = arguments?.getParcelable<User>(Constants.USER_ARGUMENT_KEY)

        user?.let {
            Glide.with(requireContext()).load(it.image).into(binding.userImage)
            val adapter = PostsAdapter(it.posts)
            binding.userPosts.adapter = adapter
            binding.userPosts.addItemDecoration(
                VerticalBottomSpaceItemDecoration(
                    requireContext(),
                    Constants.LIST_VERTICAL_SPACE
                )
            )
        }

        setToolbar()
    }

    private fun setToolbar() {
        binding.toolbarLayout.materialToolbar.run {
            title = getString(R.string.label_user_posts_fragment)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}
