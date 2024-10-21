package com.example.kilohealth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kilohealth.feature.dashboard.DashBoardScreen

@Composable
fun RootNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Route.SPLASH
    ) {

        SplashGraph(navHostController = navHostController)
        composable(
            route = Route.HEALTH
        ) {
            DashBoardScreen()
        }
    }
}