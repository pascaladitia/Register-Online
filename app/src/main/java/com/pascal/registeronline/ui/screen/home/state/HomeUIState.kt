package com.pascal.registeronline.ui.screen.home.state

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope

@ExperimentalSharedTransitionApi
data class HomeUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val sharedTransitionScope: SharedTransitionScope? = null,
    val animatedVisibilityScope: AnimatedVisibilityScope? = null
)