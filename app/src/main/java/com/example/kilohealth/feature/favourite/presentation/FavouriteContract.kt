package com.example.kilohealth.feature.favourite.presentation

import com.example.kilohealth.feature.favourite.domain.model.FavouriteListModel

class FavouriteContract {

    data class State(
        val favState : List<FavouriteListModel> = emptyList()
    )
    sealed interface Event{
        data object back: Event
    }
    sealed interface Effect{
        data object back: Effect
        sealed interface Nav{
            data object back: Nav
        }
    }
}