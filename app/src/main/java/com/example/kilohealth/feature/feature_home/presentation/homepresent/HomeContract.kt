package com.example.kilohealth.feature.feature_home.presentation.homepresent

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.kilohealth.data.PagerData
import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel
import com.example.kilohealth.feature.feature_home.domain.model.CategoryListModel

class HomeContract {
    data class State(
        val isLoading: Boolean = false,
        val homeBlogState: SnapshotStateList<BlogListModel> = mutableStateListOf(),
        val pagerState : PagerData = PagerData(image = listOf()),
        val refreshPage: Boolean = false,
        val categoryState: List<CategoryListModel> = emptyList()
    )
    sealed interface Event{
        data class Detail(val id: Int): Event
        data object IsRefresh : Event
        data object Favourite: Event
        data class ToggleFavourite(val index: Int): Event
        data object Search: Event

    }
    sealed interface Effect{

        sealed interface Nav:Effect{
            data class Detail(val id: Int): Effect,Nav
            data class ShowError(val message:String) : Effect
            data object Favourite: Effect,Nav
            data object Search: Effect,Nav
        }
    }
}