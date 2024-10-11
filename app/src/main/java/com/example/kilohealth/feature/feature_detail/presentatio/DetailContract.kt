package com.example.kilohealth.feature.feature_detail.presentatio

class DetailContract {
    sealed interface Event{
        data object back : Event
    }
    sealed interface Effect{
        data object back : Effect
        sealed interface Nav{
            data object back: Nav
        }
    }
}