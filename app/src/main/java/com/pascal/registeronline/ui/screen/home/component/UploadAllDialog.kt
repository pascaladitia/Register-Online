package com.pascal.registeronline.ui.screen.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.pascal.registeronline.R
import com.pascal.registeronline.ui.component.button.ButtonComponent
import com.pascal.registeronline.ui.component.button.ButtonOutlineComponent
import compose.icons.FeatherIcons
import compose.icons.feathericons.Upload
import compose.icons.feathericons.X

@Composable
fun UploadAllDialog(
    count: Int,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onDismiss() },
                        imageVector = FeatherIcons.X,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(Modifier.width(16.dp))

                    Text(
                        text = stringResource(R.string.label_upload_all_title),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Icon(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(id = R.drawable.image_1),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.label_upload_all_confirm),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.label_upload_all_subtitle),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                ButtonComponent(
                    text = "${stringResource(R.string.label_upload_all_action)} ($count)"
                ) {
                    onConfirm()
                }

                Spacer(modifier = Modifier.height(8.dp))

                ButtonOutlineComponent(text = stringResource(R.string.label_cancel)) {
                    onDismiss()
                }
            }
        }
    }
}