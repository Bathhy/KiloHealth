package com.example.kilohealth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.kilohealth.feature.splash.SplashScreen
import com.example.kilohealth.feature.splash.toSplashNav

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