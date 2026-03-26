package com.pascal.registeronline.ui.screen.input.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pascal.registeronline.R

@Composable
fun PreviewKtpScreen(
    imagePath: String,
    onUse: () -> Unit,
    onRetake: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .systemBarsPadding()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onRetake) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }

            Text(
                text = stringResource(R.string.label_preview_image),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.message_prepare_upload_ktp),
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(Modifier.height(16.dp))

        AsyncImage(
            model = imagePath,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFDFF5E1), RoundedCornerShape(8.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = Color(0xFF2E7D32)
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = stringResource(R.string.label_best_quality_foto),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onUse
        ) {
            Text(stringResource(R.string.label_use_this_photo))
        }

        Spacer(Modifier.height(8.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onRetake
        ) {
            Text(stringResource(R.string.label_retake_photo))
        }
    }
}