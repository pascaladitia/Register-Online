package com.pascal.registeronline.ui.screen.home.state

import com.pascal.registeronline.data.local.entity.DraftEntity
import com.pascal.registeronline.domain.model.Member

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val selectedTab: Int = 0,

    val drafts: List<DraftEntity> = emptyList(),
    val members: List<Member> = emptyList(),

    val isUploadAllDialogVisible: Boolean = false,
    val isUploadSuccess: Boolean = false,
)