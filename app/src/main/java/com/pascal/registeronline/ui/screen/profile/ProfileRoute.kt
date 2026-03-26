package com.pascal.registeronline.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.KeyboardBackspace
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pascal.registeronline.R
import com.pascal.registeronline.data.prefs.PreferencesLogin
import com.pascal.registeronline.ui.screen.profile.state.LocalProfileEvent
import com.pascal.registeronline.ui.screen.profile.state.ProfileEvent
import com.pascal.registeronline.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = koinViewModel(),
    onNavBack: () -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current

    CompositionLocalProvider(
        LocalProfileEvent provides ProfileEvent(
            onNavBack = onNavBack,
            onLogout = {
                PreferencesLogin.clear(context)
                onLogout()
            }
        )
    ) {
        ProfileScreen()
    }
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val event = LocalProfileEvent.current

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { event.onNavBack() },
                    imageVector = Icons.AutoMirrored.Filled.KeyboardBackspace,
                    contentDescription = null
                )

                Spacer(Modifier.width(16.dp))

                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(Modifier.height(24.dp))

            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp),
                painter = painterResource(R.drawable.img_profile),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Yudi Wiranto",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Menteng, Jakarta Pusat, DKI Jakarta",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "yudiwiranto12@gmail.com",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(0.1f))
            ) {
                ProfileFeature(
                    icon = Icons.Default.Password,
                    label = "Ganti Password"
                )

                ProfileFeature(
                    icon = Icons.AutoMirrored.Filled.Help,
                    label = "Bantuan"
                )
            }

            Spacer(Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(0.1f))
            ) {
                ProfileFeature(
                    icon = Icons.AutoMirrored.Filled.Logout,
                    label = "Keluar",
                    color = MaterialTheme.colorScheme.error,
                    onClick = {
                        event.onLogout()
                    }
                )
            }
        }

        Text(
            text = "v1.0.1",
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

@Composable
fun ProfileFeature(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(18.dp),
            imageVector = icon,
            contentDescription = null,
            tint = color
        )

        Spacer(Modifier.width(16.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = label,
            style = MaterialTheme.typography.titleSmall.copy(
                color = color
            )
        )

        Spacer(Modifier.width(16.dp))

        Icon(
            modifier = Modifier
                .size(18.dp),
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePreview() {
    AppTheme {
        ProfileScreen()
    }
}