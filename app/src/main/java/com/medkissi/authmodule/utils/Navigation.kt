package com.medkissi.authmodule.utils

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.medkissi.authmodule.presentation.FinalDestinationScreen
import com.medkissi.authmodule.presentation.LoginScreen
import com.medkissi.authmodule.presentation.RegisterScren

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoutes.LoginScreen.route) {
        composable(ScreenRoutes.LoginScreen.route) {
            LoginScreen(
                onLoginSuccessNavigation = {
                    navController.navigate(
                        ScreenRoutes.FinalDestination.route
                    ) {
                        popUpTo(0)
                    }

                },
                onNavigationToRegisterScreen = {
                    navController.navigate(ScreenRoutes.RegisterScreen.route) {
                        popUpTo(0)
                    }
                })


        }
        composable(ScreenRoutes.RegisterScreen.route) {
            RegisterScren(
                onRegisterSuccessNavigation = {
                    navController.navigate(ScreenRoutes.FinalDestination.route) {
                        popUpTo(0)
                    }
                },
                onNavigationToLoginScreen = {
                    navController.navigate(ScreenRoutes.LoginScreen.route) {
                        popUpTo(0)
                    }
                })

        }
        composable(ScreenRoutes.FinalDestination.route) {
            FinalDestinationScreen()


        }
    }

}

sealed class ScreenRoutes(val route: String) {
    object LoginScreen : ScreenRoutes("login_screen")
    object RegisterScreen : ScreenRoutes("register_screen")
    object FinalDestination : ScreenRoutes("home_screen")
}