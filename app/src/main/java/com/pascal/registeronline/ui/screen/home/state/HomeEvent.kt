package com.pascal.registeronline.ui.screen.home.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

val LocalHomeEvent = compositionLocalOf { HomeEvent() }

@Stable
data class HomeEvent(
    val onProfile: () -> Unit = {},
    val onTabSelected: (Int) -> Unit = {},
    val onAddData: () -> Unit = {},
    val onEditDraft: (Int) -> Unit = {},
    val onUploadDraft: (Int) -> Unit = {},
    val onShowUploadAllDialog: () -> Unit = {},
    val onDismissUploadAllDialog: () -> Unit = {},
    val onConfirmUploadAll: () -> Unit = {},
    val onDismissError: () -> Unit = {},
)