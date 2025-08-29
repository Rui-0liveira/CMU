package com.example.cmu.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cmu.ui.screens.PlaceListScreen
import com.example.cmu.ui.screens.PlaceDetailScreen

sealed class Screen(val route: String) {
    object PlaceList : Screen("place_list")
    object PlaceDetail : Screen("place_detail/{placeId}") {
        fun createRoute(placeId: Int) = "place_detail/$placeId"
    }
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.PlaceList.route) {
        composable(Screen.PlaceList.route) {
            PlaceListScreen(navController)
        }
        composable(Screen.PlaceDetail.route) { backStackEntry ->
            val placeId = backStackEntry.arguments?.getString("placeId")?.toIntOrNull()
            placeId?.let {
                PlaceDetailScreen(placeId)
            }
        }
    }
}
