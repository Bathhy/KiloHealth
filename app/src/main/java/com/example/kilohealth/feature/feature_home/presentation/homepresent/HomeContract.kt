package com.example.kilohealth.feature.feature_home.presentation.homepresent

import com.example.kilohealth.data.PagerData
import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel

class HomeContract {
    data class State(
        val isLoading: Boolean = false,
        val homeBlogState: List<BlogListModel> = emptyList(),
        val pagerState : PagerData = PagerData(image = listOf())
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