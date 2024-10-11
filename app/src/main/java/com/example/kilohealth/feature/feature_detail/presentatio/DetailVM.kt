package com.example.kilohealth.feature.feature_detail.presentatio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class DetailVM: ViewModel() {
    private val _effect = MutableSharedFlow<DetailContract.Effect>()
    val effect : SharedFlow<DetailContract.Effect> = _effect.asSharedFlow()

    fun onEvent(event: DetailContract.Event){
        when(event){
            DetailContract.Event.back -> {
                viewModelScope.launch {
                    _effect.emit(DetailContract.Effect.back)
                }
            }
        }
    }
}