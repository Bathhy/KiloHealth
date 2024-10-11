package com.example.kilohealth.feature.message.detailmessage.presentation

class DetailMessageContract {
    sealed interface Event{
        data object back: Event
    }
    sealed interface Effect{
        data object back: Effect
        sealed interface Nav{
            data object back : Nav
        }
    }
}