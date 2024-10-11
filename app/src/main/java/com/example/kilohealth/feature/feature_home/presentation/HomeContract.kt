package com.example.kilohealth.feature.feature_home.presentation

class HomeContract {
    sealed interface Event{
        data object detail: Event
    }
    sealed interface Effect{
        data object detail: Effect
        sealed interface Nav{
            data object detail:Nav
        }
    }
}