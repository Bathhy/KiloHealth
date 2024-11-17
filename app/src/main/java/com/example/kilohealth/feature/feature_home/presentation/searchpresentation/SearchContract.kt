package com.example.kilohealth.feature.feature_home.presentation.searchpresentation

import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel

class SearchContract {
    data class State(
        val searchList :List<BlogListModel> = emptyList(),
        val query:String= "",
        val searchNotFound : Boolean = false
    )
    sealed interface Event{
        data object Back :Event
        data class QuerySearch(val query:String): Event
        data object SearchBlog: Event
        data object ClearQuery:Event
        data class NavToDetail(val id: Int):Event
    }
    sealed interface Effect{
        sealed interface Nav: Effect{
            data object Back:Nav,Effect
            data class NavToDetail(val id: Int):Nav,Effect
        }
    }
}