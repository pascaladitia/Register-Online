package com.pascal.registeronline.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pascal.registeronline.ui.navigation.RouteScreen
import com.pascal.registeronline.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme(
                darkTheme = false,
                dynamicColor = false
            ) {
                RouteScreen()
            }
        }
    }
}