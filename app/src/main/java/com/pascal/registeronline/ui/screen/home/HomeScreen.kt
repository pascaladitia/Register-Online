package com.pascal.registeronline.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pascal.registeronline.R
import com.pascal.registeronline.ui.component.button.ButtonComponent
import com.pascal.registeronline.ui.component.button.ButtonOutlineComponent
import com.pascal.registeronline.ui.screen.home.component.DraftTab
import com.pascal.registeronline.ui.screen.home.component.HomeTopBar
import com.pascal.registeronline.ui.screen.home.component.UploadAllDialog
import com.pascal.registeronline.ui.screen.home.component.UploadSuccessSnackbar
import com.pascal.registeronline.ui.screen.home.component.UploadedTab
import com.pascal.registeronline.ui.screen.home.state.HomeUiState
import com.pascal.registeronline.ui.screen.home.state.LocalHomeEvent
import com.pascal.registeronline.ui.theme.AppTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.Plus
import compose.icons.feathericons.Upload

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState = HomeUiState()
) {
    val event = LocalHomeEvent.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        HomeTopBar()

        TabRow(
            selectedTabIndex = uiState.selectedTab,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[uiState.selectedTab]),
                    color = MaterialTheme.colorScheme.primary
                )
            },
            containerColor = Color.White
        ) {
            Tab(
                selected = uiState.selectedTab == 0,
                onClick = { event.onTabSelected(0) },
                text = {
                    Text(
                        text = stringResource(R.string.label_tab_draft),
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = if (uiState.selectedTab == 0)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            )
            Tab(
                selected = uiState.selectedTab == 1,
                onClick = { event.onTabSelected(1) },
                text = {
                    Text(
                        text = stringResource(R.string.label_tab_uploaded),
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = if (uiState.selectedTab == 1)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            )
        }

        Box(modifier = Modifier.weight(1f)) {
            when (uiState.selectedTab) {
                0 -> DraftTab(drafts = uiState.drafts)
                1 -> UploadedTab(members = uiState.members)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            ButtonComponent(
                text = stringResource(R.string.label_tambah_data),
                isIcon = 1,
                icon = FeatherIcons.Plus
            ) {
                event.onAddData()
            }

            Spacer(modifier = Modifier.height(8.dp))

            ButtonOutlineComponent(
                text = if (uiState.drafts.isEmpty())
                    stringResource(R.string.label_upload_semua)
                else
                    "${stringResource(R.string.label_upload_semua)} (${uiState.drafts.size})",
                isIcon = 1,
                icon = FeatherIcons.Upload,
                enabled = uiState.drafts.isNotEmpty()
            ) {
                event.onShowUploadAllDialog()
            }
        }
    }

    if (uiState.isUploadSuccess) {
        UploadSuccessSnackbar()
    }

    if (uiState.isUploadAllDialogVisible) {
        UploadAllDialog(
            count = uiState.drafts.size,
            onConfirm = event.onConfirmUploadAll,
            onDismiss = event.onDismissUploadAllDialog
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomePreview() {
    AppTheme {
        HomeScreen()
    }
}