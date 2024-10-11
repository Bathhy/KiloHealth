package com.example.kilohealth.feature.message.homemessage.presentation

class MessageContract {
    sealed interface Event{
        data object goToDeTailMessage: Event
    }
    sealed interface Effect{
        data object goToDeTailMessage: Effect
        sealed interface Nav{
            data object goToDeTailMessage: Nav
        }
    }

}