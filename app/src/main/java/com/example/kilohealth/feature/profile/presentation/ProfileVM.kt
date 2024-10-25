package com.example.kilohealth.feature.profile.presentation

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.Uri
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProfileVM : ViewModel() {

    private val _state = MutableStateFlow(ProfileContract.State())
    val uiState = _state.asStateFlow()
    private val _effect = MutableSharedFlow<ProfileContract.Effect.Nav>()
    val effect = _effect.asSharedFlow()
    fun onEvent(event: ProfileContract.Event) {
        when (event) {
            ProfileContract.Event.openCamera -> {
                viewModelScope.launch {
//                    _effect.emit(ProfileContract.Effect.Nav.openCamera)
//                    openCamera()
                }
            }
        }
    }

    private fun openCamera(){
        _state.value = _state.value.copy(
            isOpenCamera = true
        )
    }

}