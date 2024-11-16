package com.example.kilohealth.feature.feature_home.presentation.detailpresent

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kilohealth.feature.feature_home.domain.model.DetailBlogModel
import com.example.kilohealth.feature.feature_home.domain.usecase.GetDetailBlogUseCase
import com.example.kilohealth.networkconfig.XResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import kotlin.time.Duration.Companion.seconds

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
            DetailContract.Event.Back -> {
                viewModelScope.launch {
                    _effect.emit(DetailContract.Effect.Back)
                }
            }



            DetailContract.Event.RefreshDetailScreen -> {
                refreshDetailScreen()
            }
        }
    }
    init {
        refreshDetailScreen()
    }
    private fun getDetailBlog(){
        viewModelScope.launch {
            Log.d("getID", "getDetailBlog:$id")
            when(val res = getDetailUS.invoke(id)){
                is XResource.Error -> {
                    Log.d("ErrorVM", "getDetailBlog: ${res.error} ")
                    _state.value = _state.value.copy(
                        uiState = DetailBlogModel(
                            id = 5688,
                            type = "Heaven",
                            name = "Yen",
                            description = "Nabil",
                            content = "Lucky",
                            thumbnail = "Andreana",
                            views = 9870L,
                            status = "Kendale",
                            favorite = false,
                            createdAt = "Dj",
                            categories = listOf(),
                            tags = listOf()

                        )
                    )
                }
                is XResource.Success ->{
                    _state.value = _state.value.copy(
                        uiState = res.data
                    )
                }
            }

            Log.d("Detail", "getDetailBlog:${_state.value}")
        }
    }
    private fun refreshDetailScreen(){
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isRefresh = true
            )
            delay(0.5.seconds)
            getDetailBlog()
            _state.value = _state.value.copy(
                isRefresh = false
            )
        }
    }
}