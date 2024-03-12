package com.example.test_first.core.util.extantion

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.example.test_first.R
import timber.log.Timber

fun NavController.navigateSafe(
    direction: NavDirections,
    onNavigationFailed: (() -> Unit)? = null
) {
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in)
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.slide_out)
        .build()
    try {
        navigate(direction, navOptions)
    } catch (exception: Exception) {
        Timber.e(exception)
        onNavigationFailed?.invoke()
    }
}
