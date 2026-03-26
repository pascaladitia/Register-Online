package com.pascal.registeronline.ui.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pascal.registeronline.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    onBookMark: () -> Unit
) {

}

@Preview(showBackground = true)
@Composable
private fun ProfilePreview() {
    AppTheme {
    }
}