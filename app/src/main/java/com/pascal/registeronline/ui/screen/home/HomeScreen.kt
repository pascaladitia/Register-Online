@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.pascal.registeronline.ui.screen.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: HomeViewModel = koinViewModel(),
) {}