package com.example.kilohealth.feature.favourite.presentation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.kilohealth.navigation.Screen
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.toFavRoute(
    setEffect:(FavouriteContract.Effect.Nav)-> Unit
) = composable(
    route = Screen.Favorite.route,
    content = {
        val vm: FavouriteVM = koinViewModel()
        val state = vm.state.collectAsState()
        LaunchedEffect(Unit){
            vm.effect.onEach {
                when(it){
                    FavouriteContract.Effect.Nav.Back -> setEffect(FavouriteContract.Effect.Nav.Back)
                }
            }.count()
        }
        FavouriteScreen(
            setEvent =vm:: onEvent,
            uiState = state.value
        )
    }
)