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
        )
    )
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