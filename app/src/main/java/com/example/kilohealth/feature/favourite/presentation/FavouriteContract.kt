package com.example.kilohealth.feature.favourite.presentation

import com.example.kilohealth.feature.favourite.domain.model.FavouriteListModel

class FavouriteContract {

    data class State(
        val favState : MutableList<FavouriteListModel> = mutableListOf()

    )
    sealed interface Event{
        data object Back: Event
        data class RemoveFavorite(val id:Int):Event
    }
    sealed interface Effect{

        sealed interface Nav:Effect{
            data object Back: Effect,Nav
        }
    }
}