package com.example.kilohealth.feature.notification.presentation

import androidx.lifecycle.ViewModel
import com.example.kilohealth.data.FakeData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class NotificationVM(

) : ViewModel(){
    private val _state = MutableStateFlow(NotificationContract.State())
    val state = _state.asStateFlow()
    fun onEvent(event: NotificationContract.Event){
        when(event){
            NotificationContract.Event.ClearNotification -> {
                clearNotification()
            }
        }
    }
    init {

     getNotification()
    }
    private fun getNotification(){
        _state.value = _state.value.copy(
            notificationListData = FakeData.notificationFakeData
        )
    }
    private fun clearNotification(){
        _state.value = _state.value.copy(
            notificationListData = emptyList()
        )
    }
}