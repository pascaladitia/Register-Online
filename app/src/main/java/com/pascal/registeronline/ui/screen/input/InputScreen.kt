package com.pascal.registeronline.ui.screen.input

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pascal.registeronline.ui.screen.input.state.InputUiState
import com.pascal.registeronline.ui.theme.AppTheme

@Composable
fun InputScreen(
    modifier: Modifier = Modifier,
    uiState: InputUiState = InputUiState()
) {

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun InputPreview() {
    AppTheme {
        InputScreen()
    }
}