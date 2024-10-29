package com.example.kilohealth.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            ProfileContract.Event.OpenGallery -> {
                viewModelScope.launch {
//                    _effect.emit(ProfileContract.Effect.Nav.openCamera)
//                    openBottomSheet()
                }
            }

            ProfileContract.Event.OpenBottomSheet -> {
                viewModelScope.launch {
                    openBottomSheet()
                }
            }

            ProfileContract.Event.OpenCamera -> TODO()
        }
    }
     fun openBottomSheet(){
        _state.value = _state.value.copy(
            isBottomSheet = true
        )
    }

}