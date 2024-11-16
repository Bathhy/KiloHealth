package com.example.kilohealth.feature.notification.presentation

import com.example.kilohealth.data.NotificationData

class NotificationContract {
    data class State(
        val loading:Boolean= false,
        var notificationListData: List<NotificationData> = emptyList()
    )

    sealed interface Event{
        data object ClearNotification: Event

    }

}