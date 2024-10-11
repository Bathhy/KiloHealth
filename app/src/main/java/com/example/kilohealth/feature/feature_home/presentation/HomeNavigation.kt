package com.example.kilohealth.feature.feature_home.presentation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.kilohealth.navigation.Screen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.toHomeRoute(
    setEffect: (HomeContract.Effect.Nav) -> Unit
) = composable(
    route = Screen.HOme.route,
    content = {
        val vm: HomeVM = koinViewModel()
        LaunchedEffect(Unit) {
            vm.effect.onEach {
                when (it) {
                    HomeContract.Effect.detail -> {
                        setEffect(HomeContract.Effect.Nav.detail)
                    }
                }
            }.collect()
        }
        HomeScreen(setEvent = vm::onEvent)
    }
)