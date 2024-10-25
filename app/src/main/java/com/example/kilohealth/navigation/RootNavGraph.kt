package com.example.kilohealth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.kilohealth.feature.dashboard.toDashBoardScreen

@Composable
fun RootNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Route.SPLASH
    ) {

        SplashGraph(navHostController = navHostController)
       toDashBoardScreen()
    }
}