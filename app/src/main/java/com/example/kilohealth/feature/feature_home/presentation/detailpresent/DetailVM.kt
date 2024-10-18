package com.example.kilohealth.feature.feature_home.presentation.detailpresent

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kilohealth.feature.feature_home.domain.usecase.GetDetailBlogUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class DetailVM(
    private val getDetailUS: GetDetailBlogUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val id: Int = savedStateHandle.get<Int>("id") ?: 0
    private val _state = MutableStateFlow(DetailContract.State())
    val state = _state.asStateFlow()
    private val _effect = MutableSharedFlow<DetailContract.Effect>()
    val effect : SharedFlow<DetailContract.Effect> = _effect.asSharedFlow()

    fun onEvent(event: DetailContract.Event){
        when(event){
            DetailContract.Event.back -> {
                viewModelScope.launch {
                    _effect.emit(DetailContract.Effect.back)
                }
            }

            DetailContract.Event.fav ->{
                viewModelScope.launch {
                    _effect.emit(DetailContract.Effect.fav)
                }
            }
        }
    }
    init {
        getDetailBlog()
    }
    private fun getDetailBlog(){
        viewModelScope.launch {
            Log.d("getID", "getDetailBlog:$id")
            val res = getDetailUS.invoke(id)
            _state.value = _state.value.copy(
                uiState = res
            )
            Log.d("Detail", "getDetailBlog:${_state.value}")
        }
    }
}