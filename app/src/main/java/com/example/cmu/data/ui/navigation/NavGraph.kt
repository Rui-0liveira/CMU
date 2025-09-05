package com.example.cmu.data.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cmu.data.ui.screens.LoginScreen
import com.example.cmu.data.ui.screens.PlaceListScreen
import com.example.cmu.data.ui.screens.PlaceDetailScreen
import com.example.cmu.data.ui.screens.RegisterScreen
import com.example.cmu.data.ui.screens.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object PlaceList : Screen("place_list")
    object PlaceDetail : Screen("place_detail/{placeId}") {
        fun createRoute(placeId: Int) = "place_detail/$placeId"
    }
    object Login : Screen("login")
    object Register : Screen("register")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.PlaceList.route) { PlaceListScreen(navController) }
        composable(Screen.PlaceDetail.route) { backStackEntry ->
            val placeId = backStackEntry.arguments?.getString("placeId")?.toIntOrNull()
            placeId?.let { PlaceDetailScreen(navController, it) }
        }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Register.route) { RegisterScreen(navController) }
    }
}
