@file:OptIn(
    ExperimentalMotionApi::class,
    ExperimentalSharedTransitionApi::class
)

package com.pascal.registeronline.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pascal.registeronline.data.prefs.PreferencesLogin
import com.pascal.registeronline.ui.screen.home.HomeRoute
import com.pascal.registeronline.ui.screen.input.InputRoute
import com.pascal.registeronline.ui.screen.login.LoginRoute
import com.pascal.registeronline.ui.screen.profile.ProfileRoute
import com.pascal.registeronline.ui.screen.register.RegisterRoute
import com.pascal.registeronline.ui.screen.splash.SplashScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RouteScreen(
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current

    Scaffold { paddingValues ->
        SharedTransitionLayout {
            NavHost(
                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                navController = navController,
                startDestination = Screen.SplashScreen.route,
            ) {
                composable(route = Screen.SplashScreen.route) {

                    val isLogin = PreferencesLogin.getIsLogin(context)

                    SplashScreen {
                        navController.navigate(
                            if (isLogin) {
                                Screen.HomeScreen.route
                            } else {
                                Screen.LoginScreen.route
                            }
                        ) {
                            popUpTo(Screen.SplashScreen.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                }
                composable(route = Screen.LoginScreen.route) {
                    LoginRoute(
                        onRegister = {
                            navController.navigate(Screen.RegisterScreen.route)
                        },
                        onLogin = {
                            navController.navigate(Screen.HomeScreen.route) {
                                popUpTo(Screen.LoginScreen.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }
                composable(route = Screen.RegisterScreen.route) {
                    RegisterRoute(
                        onLogin = {
                            navController.popBackStack()
                        }
                    )
                }
                composable(route = Screen.HomeScreen.route) {
                    HomeRoute(
                        onAddData = {
                            navController.navigate(Screen.InputScreen.route)
                        },
                        onProfile = {
                            navController.navigate(Screen.ProfileScreen.route)
                        }
                    )
                }
                composable(route = Screen.InputScreen.route) {
                    InputRoute(
                        onNavBack = {
                            navController.popBackStack()
                        }
                    )
                }
                composable(route = Screen.ProfileScreen.route) {
                    ProfileRoute(
                        onNavBack = {
                            navController.popBackStack()
                        },
                        onLogout = {
                            navController.navigate(Screen.LoginScreen.route) {
                                popUpTo(Screen.LoginScreen.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        },
                    )
                }
            }
        }
    }
}