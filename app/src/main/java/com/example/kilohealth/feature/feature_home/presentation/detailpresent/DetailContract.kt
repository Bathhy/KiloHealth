package com.example.kilohealth.feature.feature_home.presentation.detailpresent

import com.example.kilohealth.feature.feature_home.domain.model.DetailBlogModel

class DetailContract {
    data class State(
        val uiState: DetailBlogModel = DetailBlogModel(
            id = 7856,
            type = "Prentis",
            name = "Mccall",
            description = "Sarra",
            content = "Jordyn",
            thumbnail = "Shanetta",
            views = 4022L,
            status = "Emmitt",
            favorite = false,
            createdAt = "Yecenia",
            categories = listOf(),
            tags = listOf()
        ),
        val isRefresh : Boolean = false
    )
    sealed interface Event{
        data object Back : Event
        data object Fav: Event
        data object RefreshDetailScreen : Event
    }
    sealed interface Effect{
        data object Back : Effect
        data object Fav: Effect

        sealed interface Nav{
            data object Back: Nav
            data object Fav: Nav

        }
    }
}