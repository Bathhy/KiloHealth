package com.example.kilohealth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.kilohealth.feature.splash.SplashScreen

fun NavGraphBuilder.SplashGraph(
    navHostController: NavHostController
){
    navigation(
        route = Route.SPLASH,
        startDestination = Screen.Splash.route,

    ){
        composable (Screen.Splash.route){
            SplashScreen(navHostController = navHostController)
        }
    }
}