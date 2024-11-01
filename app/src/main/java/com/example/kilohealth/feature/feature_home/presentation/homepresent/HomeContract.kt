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
        data class detail(val id: Int): Effect
        sealed interface Nav{
            data class detail(val id:Int) : Nav
        }
    }
}