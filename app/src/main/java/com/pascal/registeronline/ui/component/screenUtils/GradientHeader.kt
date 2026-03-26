package com.pascal.registeronline.ui.component.screenUtils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GradientHeader(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    val horizontalGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFF5F5F7),
            Color(0xFFDCD9F4),
            Color(0xFFE9CFF5)
        )
    )

    val verticalFade = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            MaterialTheme.colorScheme.background
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 120.dp)
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .background(horizontalGradient)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(verticalFade)
        )

        content()
    }
}