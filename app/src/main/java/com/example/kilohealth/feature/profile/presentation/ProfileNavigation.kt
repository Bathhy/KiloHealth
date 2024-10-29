//package com.example.kilohealth.feature.profile.presentation
//
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.PickVisualMediaRequest
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.runtime.collectAsState
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.compose.composable
//import com.example.kilohealth.R
//import com.example.kilohealth.navigation.Screen
//import com.example.kilohealth.x_component.XIcon
//import org.koin.androidx.compose.koinViewModel
//
//fun NavGraphBuilder.toProfileRoute() = composable(
//    route = Screen.Profile.route,
//    content = {
//        val vm: ProfileVM = koinViewModel()
//        val state = vm.uiState.collectAsState()
//        val pickPhoto =
//            rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
//
//            }
//        ProfileScreen(
//            state = state.value,
//            setEvent = {
//                when (it) {
//                    ProfileContract.Event.openCamera -> {
//                        pickPhoto.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageAndVideo))
//                    }
//                }
//            }
//        )
//    }
//)