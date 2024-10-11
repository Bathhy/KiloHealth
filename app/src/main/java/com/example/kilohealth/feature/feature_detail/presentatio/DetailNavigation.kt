package com.example.kilohealth.feature.feature_detail.presentatio

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.kilohealth.navigation.Screen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.toDetailRoute(
    setEffect: (DetailContract.Effect.Nav) -> Unit
) = composable(
    route = Screen.Detail.route,
    content = {
        val detailVM: DetailVM = koinViewModel()
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
            setEvent = detailVM::onEvent
        )
    }
)