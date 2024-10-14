package com.example.kilohealth.feature.feature_home.presentation.homepresent

import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel

class HomeContract {
    data class State(
        val homeBlogState: List<BlogListModel> = emptyList()
    )
    sealed interface Event{
        data class detail(val id: Int): Event
    }
    sealed interface Effect{
        data class detail(val id: Int): Effect
        sealed interface Nav{
            data class detail(val id:Int) : Nav
        }
    }
}