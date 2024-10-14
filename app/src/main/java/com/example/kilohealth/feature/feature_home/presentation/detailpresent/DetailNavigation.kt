package com.example.kilohealth.feature.feature_home.presentation.detailpresent

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kilohealth.navigation.Screen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.toDetailRoute(
    setEffect: (DetailContract.Effect.Nav) -> Unit
) = composable(
    arguments = listOf(navArgument(name = "id"){
        type = NavType.IntType
    }),
    route = Screen.Detail(id).route,
    content = {
        val detailVM: DetailVM = koinViewModel()
        val state = detailVM.state.collectAsState()
        LaunchedEffect(Unit) {
            detailVM.effect.onEach {
                when (it) {
                    DetailContract.Effect.back -> {
                        setEffect(DetailContract.Effect.Nav.back)
                    }
                }
            }.collect()
        }
        DetailScreen(
            setEvent = detailVM::onEvent,
            state.value
        )
    }
)