package com.example.kilohealth.feature.feature_home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kilohealth.feature.feature_home.data.datasource.HomeEndPoint
import com.example.kilohealth.feature.feature_home.domain.usecase.GetBlogListUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeVM(
    private val getBlogListUS: GetBlogListUseCase
): ViewModel() {
    private val _state = MutableStateFlow(HomeContract.State())
    val state = _state.asStateFlow()
    private val _effect = MutableSharedFlow<HomeContract.Effect>()
    val effect: SharedFlow<HomeContract.Effect> = _effect.asSharedFlow()
    fun onEvent(event: HomeContract.Event) {
        when (event) {
            HomeContract.Event.detail -> {
                viewModelScope.launch {
                    _effect.emit(HomeContract.Effect.detail)
                }
            }
        }
    }

    init {
        getBlogList()
    }
    private fun getBlogList(){
        viewModelScope.launch {
            val res = getBlogListUS.invoke()
            _state.value = _state.value.copy(
                homeBlogState = res
            )
            Log.d("datat", "getBlogList:${res}")
        }
    }
}