package com.metinvandar.usersandposts.utils.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showError(
    errorMessage: String,
    retry: (() -> Unit)? = null,
    errorViewDuration: Int = Snackbar.LENGTH_INDEFINITE
) {
    requireView().snackBar(message = errorMessage, action = retry, duration = errorViewDuration)
}
