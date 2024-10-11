package com.example.kilohealth.feature.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeVM : ViewModel() {
    private val _effect = MutableSharedFlow<HomeContract.Effect>()
    val effect: SharedFlow<HomeContract.Effect> = _effect.asSharedFlow()
    fun onEvent(event: HomeContract.Event) {
        when (event) {
            HomeContract.Event.detail -> {
                viewModelScope.launch {
                    _effect.emit(HomeContract.Effect.detail)
                }
            }
        }
    }
}