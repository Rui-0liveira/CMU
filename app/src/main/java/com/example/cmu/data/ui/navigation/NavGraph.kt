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
import com.example.cmu.ui.screens.MapScreen
import com.google.firebase.auth.FirebaseAuth

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object PlaceList : Screen("place_list")
    object PlaceDetail : Screen("place_detail/{placeId}") {
        fun createRoute(placeId: Int) = "place_detail/$placeId"
    }
    object Login : Screen("login")
    object Register : Screen("register")

    object Map : Screen("map")
}

/*@Composable
fun AppNavGraph(navController: NavHostController, hasLocationPermission: Boolean) {
    NavHost(navController = navController, startDestination = Screen.Map.route) {
        composable(Screen.Map.route) {
            MapScreen(
                hasLocationPermission = hasLocationPermission,
                onMarkerClick = { placeId ->
                    navController.navigate("estabelecimento_detail/$placeId")
                }
            )
        }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.PlaceList.route) { PlaceListScreen(navController) }
        composable(Screen.PlaceDetail.route) { backStackEntry ->
            val placeId = backStackEntry.arguments?.getString("placeId")?.toIntOrNull()
            placeId?.let { PlaceDetailScreen(navController, it) }
        }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Register.route) { RegisterScreen(navController) }
    }
}*/
@Composable
fun AppNavGraph(navController: NavHostController, hasLocationPermission: Boolean) {
    // Verifica se já há sessão iniciada
    val auth = FirebaseAuth.getInstance()
    val startDestination = if (auth.currentUser != null) {
        Screen.Home.route
    } else {
        Screen.Login.route
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Home.route) { HomeScreen(navController) }

        composable(Screen.Map.route) {
            MapScreen(
                hasLocationPermission = hasLocationPermission,
                onMarkerClick = { placeId ->
                    navController.navigate("place_detail/$placeId")
                }
            )
        }

        composable(Screen.PlaceList.route) { PlaceListScreen(navController) }

        composable(Screen.PlaceDetail.route) { backStackEntry ->
            val placeId = backStackEntry.arguments?.getString("placeId")?.toIntOrNull()
            placeId?.let { PlaceDetailScreen(navController, it) }
        }

        composable(Screen.Login.route) { LoginScreen(navController) }

        composable(Screen.Register.route) { RegisterScreen(navController) }
    }
}

