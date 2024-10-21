package com.example.kilohealth.feature.feature_home.presentation.homepresent

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
        val uiState = vm.state.collectAsState()
        LaunchedEffect(Unit) {
            vm.effect.onEach {
                when (it) {
                    is HomeContract.Effect.detail -> {
                        val route = Screen.Detail(id = it.id)
//                        Log.d("TAG", "toHomeRoute:${it.id}")
                        setEffect(
                            HomeContract.Effect.Nav.detail(route.id)
                        )
                    }
                }
            }.collect()
        }
        HomeScreen(setEvent = vm::onEvent ,uiState.value )
    }
)