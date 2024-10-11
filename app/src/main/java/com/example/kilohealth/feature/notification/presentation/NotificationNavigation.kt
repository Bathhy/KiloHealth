package com.example.kilohealth.feature.notification.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.kilohealth.navigation.Screen

fun NavGraphBuilder.toNotificationRoute() = composable(
    route = Screen.Notification.route,
    content = {
        NotificationScreen()
    }
)