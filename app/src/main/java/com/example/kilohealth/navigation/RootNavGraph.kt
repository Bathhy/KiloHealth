package com.example.kilohealth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.kilohealth.feature.dashboard.toDashBoardScreen
import com.example.kilohealth.feature.favourite.presentation.FavouriteContract
import com.example.kilohealth.feature.favourite.presentation.toFavRoute
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
fun RootNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Route.SPLASH
    ) {

        SplashGraph(navHostController = navHostController)
       toDashBoardScreen(navHostController)
        toDetailRoute(
            setEffect = {
                when(it){
                    DetailContract.Effect.Nav.back -> {
                        navHostController.popBackStack()
                    }

                    DetailContract.Effect.Nav.fav -> {
                        navHostController.navigate(Screen.Favorite.route)
                    }
                }
            }
        )
        toProfileRoute()
        toNotificationRoute()
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

        toFavRoute(
            setEffect = {
                when(it){
                    FavouriteContract.Effect.Nav.back -> {
                        navHostController.popBackStack()
                    }
                }
            }
        )
    }
}