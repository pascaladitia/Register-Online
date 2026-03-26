@file:OptIn(
    ExperimentalMotionApi::class,
    ExperimentalSharedTransitionApi::class
)

package com.pascal.registeronline.ui.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pascal.registeronline.ui.screen.home.HomeScreen
import com.pascal.registeronline.ui.screen.profile.ProfileScreen
import com.pascal.registeronline.ui.screen.splash.SplashScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RouteScreen(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(
                    Screen.HomeScreen.route,
                    Screen.EPaperScreen.route,
                    Screen.TTSScreen.route,
                    Screen.BookScreen.route,
                    Screen.ProfileScreen.route
                )) {
                BottomBar(navController)
            }
        }
    ) { paddingValues ->
        SharedTransitionLayout {
            val sharedScope: SharedTransitionScope = this

            NavHost(
                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                navController = navController,
                startDestination = Screen.SplashScreen.route,
            ) {
                composable(route = Screen.SplashScreen.route) {
                    SplashScreen(
                        paddingValues = paddingValues
                    ) {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.SplashScreen.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                }
                composable(route = Screen.HomeScreen.route) {
                    val animScope: AnimatedVisibilityScope = this
                    HomeScreen(
                        sharedTransitionScope = sharedScope,
                        animatedVisibilityScope = animScope
                    )
                }
                composable(route = Screen.ProfileScreen.route) {
                    ProfileScreen(
                        onBookMark = {
                            navController.navigate(Screen.BookmarkScreen.route)
                        },
                    )
                }
            }
        }
    }
}