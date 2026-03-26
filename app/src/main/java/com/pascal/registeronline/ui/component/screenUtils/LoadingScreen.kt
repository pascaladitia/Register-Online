package com.pascal.registeronline.ui.component.screenUtils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.pascal.registeronline.R
import com.pascal.registeronline.ui.theme.AppTheme

@Composable
fun LoadingScreen() {
    Dialog(
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false,
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = { },
    ) {
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(R.drawable.loading1)
            .decoderFactory(GifDecoder.Factory())
            .crossfade(true)
            .build()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(64.dp),
                model = imageRequest,
                contentDescription = "GIF"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    AppTheme {
        LoadingScreen()
    }
}