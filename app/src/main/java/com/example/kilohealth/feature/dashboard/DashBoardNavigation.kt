package com.example.kilohealth.feature.dashboard

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.kilohealth.data.FakeData
import com.example.kilohealth.feature.feature_home.presentation.homepresent.HomeContract
import com.example.kilohealth.feature.feature_home.presentation.homepresent.HomeScreen
import com.example.kilohealth.feature.feature_home.presentation.homepresent.HomeVM
import com.example.kilohealth.feature.message.homemessage.presentation.MessageContract
import com.example.kilohealth.feature.message.homemessage.presentation.MessageScreen
import com.example.kilohealth.feature.message.homemessage.presentation.MessageVM
import com.example.kilohealth.feature.notification.presentation.NotificationScreen
import com.example.kilohealth.feature.notification.presentation.NotificationVM
import com.example.kilohealth.feature.profile.presentation.ProfileContract
import com.example.kilohealth.feature.profile.presentation.ProfileScreen
import com.example.kilohealth.feature.profile.presentation.ProfileVM
import com.example.kilohealth.navigation.Route
import com.example.kilohealth.navigation.Screen
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XIcon
import com.example.kilohealth.x_component.XPadding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.toDashBoardScreen(navHostController: NavHostController) = composable(
    route = Route.HEALTH,
    content = {
        DashBoardScreen(navHostController)
    }
)


@Composable
internal fun DashBoardScreen(
    controller: NavHostController
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState {
        4
    }
    val pickPhoto =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {

        }
    Scaffold(
        bottomBar = {
            BottomAppBar(

            ) {
                NavigationBar(

                ) {
                    FakeData.bottomNavFakeData.forEachIndexed { index, bot ->
                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Red,

                                indicatorColor = Color.Unspecified,
                                unselectedIconColor = Color.LightGray,

                                ),
                            selected = pagerState.currentPage == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }, icon = {
                                XIcon(
                                    icon = bot.icon, tint =
                                    if (pagerState.currentPage == index) {
                                        healthTheme
                                    } else {
                                        Color.LightGray
                                    }
                                )
                            })
                    }
                }
            }
        }
    ) { padding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(bottom = XPadding.extraLarge * 5)
        ) { it ->
            when (it) {
                0 -> {
                    val context = LocalContext.current
                    val vm: HomeVM = koinViewModel()
                    val uiState = vm.state.collectAsState()
                    LaunchedEffect(Unit) {

                        vm.effect.onEach {
                            when (it) {
                                is HomeContract.Effect.Nav.Detail -> {
                                    val route = Screen.Detail(id = it.id).passId(it.id)
                                    controller.navigate(route)
                                }

                                is HomeContract.Effect.Nav.ShowError -> {
                                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                                }

                                HomeContract.Effect.Nav.Favourite ->{
                                    controller.navigate(Screen.Favorite.route)
                                }

                                HomeContract.Effect.Nav.Search -> {
                                    controller.navigate(Screen.Search.route)
                                }
                            }
                        }.collect()
                    }
                    HomeScreen(setEvent = vm::onEvent, uiState.value)
                }

                1 -> {
                    val vm: MessageVM = koinViewModel()
                    LaunchedEffect(Unit) {
                        vm.effect.onEach {
                            when (it) {
                                MessageContract.Effect.goToDeTailMessage -> {
                                    controller.navigate(Screen.DetailMessage.route)
                                }
                            }
                        }.collect()
                    }
                    MessageScreen(
                        setEvent = vm::onEvent
                    )
                }

                2 -> {
                    val vm: NotificationVM = koinViewModel()
                    val uiState = vm.state.collectAsState()
                    NotificationScreen(
                        setEvent = vm::onEvent,
                        uiState.value
                    )

                }

                3 -> {
                    val vm: ProfileVM = koinViewModel()
                    val uiState = vm.uiState.collectAsState()
                    ProfileScreen(

                        state = uiState.value,
                        setEvent = {
                            when (it) {

                                ProfileContract.Event.OpenGallery -> {
                                    pickPhoto.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageAndVideo))

                                }

                                ProfileContract.Event.OpenBottomSheet -> {
                                    vm.openBottomSheet()
                                }

                                ProfileContract.Event.OpenCamera -> {
//                                    imageUri = createImage
//                                    openCamera.launch()
                                }
                            }
                        }
                    )
                }
            }

        }
    }
}