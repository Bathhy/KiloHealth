package com.example.kilohealth.feature.profile.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.kilohealth.navigation.Screen

fun NavGraphBuilder.toProfileRoute()= composable(
    route = Screen.Profile.route,
    content = {
        ProfileScreen()
    }
)