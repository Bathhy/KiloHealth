package com.example.kilohealth.feature.message.homemessage.presentation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.kilohealth.feature.message.detailmessage.presentation.DetailMessageContract
import com.example.kilohealth.feature.message.detailmessage.presentation.DetailMessageScreen
import com.example.kilohealth.feature.message.detailmessage.presentation.DetailMessageVM
import com.example.kilohealth.navigation.Screen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.toMessageRoute(
    navController: NavController,
    setEffect: (MessageContract.Effect.Nav) -> Unit
) = composable(

    route = Screen.Message.route,
    content = {
        val vm: MessageVM = koinViewModel()
        LaunchedEffect(Unit) {
            vm.effect.onEach {
                when (it) {
                    MessageContract.Effect.GoToDeTailMessage -> {
                        setEffect(MessageContract.Effect.Nav.GoToDeTailMessage)
                    }
                }
            }.collect()
        }
        MessageScreen(
            setEvent = vm::onEvent
        )
    }
)

fun NavGraphBuilder.toDetailMessageRoute(
    setEffect: (DetailMessageContract.Effect.Nav) -> Unit
) = composable(
    route = Screen.DetailMessage.route,
    content = {
        val detailMesVM: DetailMessageVM = koinViewModel()
        LaunchedEffect(Unit) {
            detailMesVM.effect.onEach {
                when(it){
                    DetailMessageContract.Effect.back -> {
                        setEffect(DetailMessageContract.Effect.Nav.back)
                    }
                }
            }.collect()
        }
        DetailMessageScreen(
            setEvent =detailMesVM::onEvent
        )
    }
)