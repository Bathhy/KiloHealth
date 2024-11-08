package com.example.kilohealth.feature.feature_home.presentation.homepresent

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
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
        val context = LocalContext.current
        val vm: HomeVM = koinViewModel()
        val uiState = vm.state.collectAsState()
        LaunchedEffect(Unit) {
            vm.effect.onEach {
                when (it) {
                    is HomeContract.Effect.Nav.Detail -> {
                        val route = Screen.Detail(it.id)
                        setEffect(
                            HomeContract.Effect.Nav.Detail(route.id)
                        )
                    }
                    is HomeContract.Effect.Nav.ShowError -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT ).show()
                    }
                }
            }.collect()
        }
        HomeScreen(setEvent = vm::onEvent ,uiState.value )
    }
)