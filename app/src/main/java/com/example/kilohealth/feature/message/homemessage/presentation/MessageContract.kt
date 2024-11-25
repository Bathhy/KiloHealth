package com.example.kilohealth.feature.message.homemessage.presentation

class MessageContract {
    sealed interface Event{
        data object GoToDeTailMessage: Event
    }
    sealed interface Effect{
        data object GoToDeTailMessage: Effect
        sealed interface Nav{
            data object GoToDeTailMessage: Nav
        }
    }

}