package com.example.kilohealth.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.kilohealth.feature.feature_home.presentation.detailpresent.DetailContract
import com.example.kilohealth.feature.feature_home.presentation.detailpresent.toDetailRoute
import com.example.kilohealth.feature.feature_home.presentation.homepresent.HomeContract
import com.example.kilohealth.feature.feature_home.presentation.homepresent.toHomeRoute
import com.example.kilohealth.feature.message.detailmessage.presentation.DetailMessageContract
import com.example.kilohealth.feature.message.homemessage.presentation.MessageContract
import com.example.kilohealth.feature.message.homemessage.presentation.toDetailMessageRoute
import com.example.kilohealth.feature.message.homemessage.presentation.toMessageRoute
import com.example.kilohealth.feature.notification.presentation.toNotificationRoute
import com.example.kilohealth.feature.profile.presentation.toProfileRoute


@Composable
fun RootNavGraph(navHostController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HOme.route
    ) {
        toHomeRoute(
            setEffect = {
                when (it) {
                    is HomeContract.Effect.Nav.detail -> {
                        val route = Screen.Detail(id = id).passId(it.id)
                        navHostController.navigate(route)
                    }
                }
            }
        )
        toMessageRoute(
            navHostController,
            setEffect = {
                when (it) {
                    MessageContract.Effect.Nav.goToDeTailMessage -> {
                        navHostController.navigate(Screen.DetailMessage.route)
                    }
                }
            }
        )
        toDetailMessageRoute(
            setEffect = {
                when (it) {
                    DetailMessageContract.Effect.Nav.back -> {
                        navHostController.popBackStack()
                    }
                }
            }
        )
        toProfileRoute()
        toNotificationRoute()
        toDetailRoute(
            setEffect = {
                when(it){
                    DetailContract.Effect.Nav.back -> {
                        navHostController.popBackStack()
                    }
                }
            }
        )
    }
}