package com.metinvandar.usersandposts.presentation.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.metinvandar.usersandposts.R
import com.metinvandar.usersandposts.databinding.FragmentUsersBinding
import com.metinvandar.usersandposts.presentation.state.UsersUIState
import com.metinvandar.usersandposts.utils.Constants
import com.metinvandar.usersandposts.utils.VerticalBottomSpaceItemDecoration
import com.metinvandar.usersandposts.utils.extensions.showError
import com.metinvandar.usersandposts.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.fragment_users) {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UsersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUsersBinding.bind(view)
        binding.toolbarLayout.materialToolbar.run {
            title = getString(R.string.label_users_fragment)
            navigationIcon = null
        }
        observeUsersState()
    }

    private fun observeUsersState() {
        viewModel.userUIState.observe(viewLifecycleOwner) { state ->
            binding.progressBar.visible = state.loading
            when (state) {
                is UsersUIState.Success -> {
                    val adapter = UsersAdapter(state.users).apply {
                        itemClick = {
                            findNavController().navigate(
                                UsersFragmentDirections.actionUsersToUsersPosts(it)
                            )
                        }
                    }
                    binding.userList.adapter = adapter
                    binding.userList.addItemDecoration(
                        VerticalBottomSpaceItemDecoration(
                            requireContext(),
                            Constants.LIST_VERTICAL_SPACE
                        )
                    )
                }
                is UsersUIState.Failed -> showError(errorMessage = state.errorMessage, retry = {
                    viewModel.getUsersAndPost()
                })
                else -> {}
            }
        }
    }
}