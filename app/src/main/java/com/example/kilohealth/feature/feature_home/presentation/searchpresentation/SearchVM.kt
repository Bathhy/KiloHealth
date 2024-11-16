package com.example.kilohealth.feature.feature_home.presentation.searchpresentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kilohealth.feature.favourite.presentation.FavouriteContract
import com.example.kilohealth.feature.feature_home.domain.usecase.GetBlogListUseCase
import com.example.kilohealth.networkconfig.XResource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SearchVM(
    private val getBlogListUseCase: GetBlogListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SearchContract.State())
    val state = _state.asStateFlow()
    private val _effect = MutableSharedFlow<SearchContract.Effect>()
    val effect: SharedFlow<SearchContract.Effect> = _effect.asSharedFlow()
    fun onEvent(
        event: SearchContract.Event
    ) {
        when (event) {
            SearchContract.Event.Back -> {
                viewModelScope.launch {
                    _effect.emit(SearchContract.Effect.Nav.Back)
                }
            }

            SearchContract.Event.SearchBlog -> {
                searchBlog()
            }
            is SearchContract.Event.QuerySearch -> {
                queryChange(event.query)
            }
            SearchContract.Event.ClearQuery -> {
                clearQuery()
            }
        }
    }

    init {
        getBlogList()
    }

    private fun getBlogList() {
        viewModelScope.launch {
            when (val res = getBlogListUseCase.invoke()) {
                is XResource.Error -> {
                    Log.d(" errorSearch", "getBlogList:${res.error}")
                }

                is XResource.Success -> {
                    Log.d(" search", "getBlogList:${res.data}")
                    _state.value = _state.value.copy(
                        searchList = res.data
                    )
                }
            }
        }
    }

    private fun clearQuery() {
        _state.value = _state.value.copy(
            query = "",
            searchNotFound = false
        )
        getBlogList()
    }

    private fun queryChange(query: String) {
        _state.value = _state.value.copy(
            query = query,
        )

    }

    private fun searchBlog() {
        viewModelScope.launch {
            when (val res = getBlogListUseCase.invoke(_state.value.query)) {
                is XResource.Error -> {
                    Log.d("erroSearch", "getBlogList:${res.error}")
                }

                is XResource.Success -> {
                    _state.value = _state.value.copy(
                        searchList = res.data,
                        searchNotFound = res.data.isEmpty()
                    )
                }
            }

        }

    }
}