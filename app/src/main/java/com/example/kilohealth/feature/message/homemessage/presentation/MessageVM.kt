package com.example.kilohealth.feature.message.homemessage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MessageVM : ViewModel(){
    private val _effect = MutableSharedFlow<MessageContract.Effect>()
    val effect : SharedFlow<MessageContract.Effect> = _effect.asSharedFlow()

    fun onEvent(event: MessageContract.Event){
        when(event){
            MessageContract.Event.goToDeTailMessage ->{
                viewModelScope.launch {
                    _effect.emit(MessageContract.Effect.goToDeTailMessage)
                }
            }
        }
    }
}