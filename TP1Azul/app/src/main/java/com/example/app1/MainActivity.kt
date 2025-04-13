package com.example.app1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app1.screens.LoginScreen
import com.example.app1.screens.RegisterScreen
import com.example.app1.screens.WelcomeScreen
import com.example.app1.ui.theme.App1Theme
import com.example.app1.ui.theme.BackgroundWhite

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App1Theme { // Aplica nuestro tema personalizado
                Surface(
                    color = BackgroundWhite,
                    content = { Navigation() }
                )
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { userName ->
                    navController.navigate("welcome/$userName")
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.popBackStack() // Vuelve al login
                }
            )
        }
        // Recibe el nombre del usuario para mostrarlo en el mensaje de bienvenida
        composable(
            "welcome/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: "Usuario"
            WelcomeScreen(userName)
        }
    }
}
