package com.example.kilohealth.feature.feature_home.presentation

import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel

class HomeContract {
    data class State(
        val homeBlogState: List<BlogListModel> = emptyList()
    )
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