package com.example.kilohealth.feature.message.detailmessage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class DetailMessageVM: ViewModel(){
    private val _effect = MutableSharedFlow<DetailMessageContract.Effect>()
    val effect : SharedFlow<DetailMessageContract.Effect> = _effect.asSharedFlow()
     fun onEvent(event:DetailMessageContract.Event){
        when(event){
            DetailMessageContract.Event.back -> {
                viewModelScope.launch {
                    _effect.emit(DetailMessageContract.Effect.back)
                }
            }
        }
    }
}