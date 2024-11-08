package com.example.kilohealth.feature.feature_home.presentation.homepresent

import com.example.kilohealth.data.PagerData
import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel

class HomeContract {
    data class State(
        val isLoading: Boolean = false,
        val homeBlogState: List<BlogListModel> = emptyList(),
        val pagerState : PagerData = PagerData(image = listOf()),
        val refreshPage: Boolean = false
    )
    sealed interface Event{
        data class Detail(val id: Int): Event
        data object IsRefresh : Event

    }
    sealed interface Effect{
        sealed interface Nav:Effect{
            data class Detail(val id: Int): Effect,Nav
            data class ShowError(val message:String) : Effect
        }
    }
}