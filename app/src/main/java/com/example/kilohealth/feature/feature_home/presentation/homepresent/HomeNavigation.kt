package com.example.kilohealth.feature.feature_home.presentation.homepresent

//fun NavGraphBuilder.toHomeRoute(
//    setEffect: (HomeContract.Effect.Nav) -> Unit
//) = composable(
//    route = Screen.HOme.route,
//    content = {
//        val context = LocalContext.current
//        val vm: HomeVM = koinViewModel()
//        val uiState = vm.state.collectAsState()
//        LaunchedEffect(Unit) {
//            vm.effect.onEach {
//                Log.d("HomeScreen", "Effect collected: $it")
//                when (it) {
//                    is HomeContract.Effect.Nav.Detail -> {
//                        val route = Screen.Detail(it.id)
//                        setEffect(
//                            HomeContract.Effect.Nav.Detail(route.id)
//                        )
//                    }
//
//                    is HomeContract.Effect.Nav.ShowError -> {
//                        Log.d("ErrorMessage", "toHomeRoute:${it.message} ")
//                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
//                    }
//
//                    HomeContract.Effect.Nav.Favourite -> {
//                        setEffect(HomeContract.Effect.Nav.Favourite)
//                    }
//
//                    HomeContract.Effect.Nav.Search -> {
//                        setEffect(HomeContract.Effect.Nav.Search)
//                    }
//                }
//            }.collect()
//        }
//        HomeScreen(setEvent = vm::onEvent, uiState.value)
//    }
//)