package com.example.kilohealth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.kilohealth.feature.auth.presentation.toSignIn
import com.example.kilohealth.feature.dashboard.toDashBoardScreen
import com.example.kilohealth.feature.favourite.presentation.FavouriteContract
import com.example.kilohealth.feature.favourite.presentation.toFavRoute
import com.example.kilohealth.feature.feature_home.presentation.detailpresent.DetailContract
import com.example.kilohealth.feature.feature_home.presentation.detailpresent.toDetailRoute
//import com.example.kilohealth.feature.feature_home.presentation.homepresent.toHomeRoute
import com.example.kilohealth.feature.feature_home.presentation.searchpresentation.SearchContract
import com.example.kilohealth.feature.feature_home.presentation.searchpresentation.toSearchRoute
import com.example.kilohealth.feature.message.detailmessage.presentation.DetailMessageContract
import com.example.kilohealth.feature.message.homemessage.presentation.MessageContract
import com.example.kilohealth.feature.message.homemessage.presentation.toDetailMessageRoute
import com.example.kilohealth.feature.message.homemessage.presentation.toMessageRoute

@Composable
fun RootNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Route.SPLASH
    ) {
toSignIn()
        SplashGraph(navHostController = navHostController)
       toDashBoardScreen(navHostController)
        toDetailRoute(
            setEffect = {
                when(it){
                    DetailContract.Effect.Nav.Back -> {
                        navHostController.popBackStack()
                    }

                    DetailContract.Effect.Nav.Fav -> {
                        navHostController.navigate(Screen.Favorite.route)
                    }
                }
            }
        )
//        toProfileRoute()
//        toNotificationRoute()
//        toHomeRoute(
//            setEffect = {
//                when (it) {
//                    is HomeContract.Effect.Nav.Detail -> {
//                        val route = Screen.Detail(id = id).passId(it.id)
//                        navHostController.navigate(route)
//                    }
//
//                    HomeContract.Effect.Nav.Favourite -> {
//                        navHostController.navigate(Screen.Favorite.route)
//                    }
//
//                    HomeContract.Effect.Nav.Search -> {
//                        navHostController.navigate(Screen.Search.route)
//                    }
//                }
//            }
//        )
        toMessageRoute(
            navHostController,
            setEffect = {
                when (it) {
                    MessageContract.Effect.Nav.GoToDeTailMessage -> {
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
                    FavouriteContract.Effect.Nav.Back -> {
                        navHostController.popBackStack()
                    }
                }
            }
        )
        toSearchRoute(
            setEffect = {
                nav->
                when(nav){
                    SearchContract.Effect.Nav.Back -> {
                        navHostController.popBackStack()
                    }

                    is SearchContract.Effect.Nav.NavToDetail -> {
                        val route = Screen.Detail(id = id).passId(id = nav.id)
                        navHostController.navigate(route)
                    }
                }
            }
        )
    }
}