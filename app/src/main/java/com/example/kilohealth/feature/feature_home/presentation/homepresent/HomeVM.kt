package com.example.kilohealth.feature.feature_home.presentation.homepresent

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kilohealth.data.PagerData
import com.example.kilohealth.feature.feature_home.domain.model.InfoSliderModel
import com.example.kilohealth.feature.feature_home.domain.usecase.GetBlogListUseCase
import com.example.kilohealth.feature.feature_home.domain.usecase.GetCategoryListUseCase
import com.example.kilohealth.feature.feature_home.domain.usecase.GetSliderInfo
import com.example.kilohealth.feature.feature_home.domain.usecase.ToggleFavoriteUseCase
import com.example.kilohealth.networkconfig.ErrorType
import com.example.kilohealth.networkconfig.MessageError
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
class HomeVM(
    private val getBlogListUS: GetBlogListUseCase,
    private val getSliderUS: GetSliderInfo,
    private val getCateUS: GetCategoryListUseCase,
    private val toggleFav: ToggleFavoriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeContract.State())
    val state = _state.asStateFlow()
    private val _effect = MutableSharedFlow<HomeContract.Effect>()
    val effect: SharedFlow<HomeContract.Effect> = _effect.asSharedFlow()

    fun onEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.Detail -> {
                viewModelScope.launch {
                    _effect.emit(HomeContract.Effect.Nav.Detail(event.id))
                }
            }

            HomeContract.Event.IsRefresh -> {
                refreshPage()
            }

            HomeContract.Event.Favourite -> {
                viewModelScope.launch {
                    _effect.emit(HomeContract.Effect.Nav.Favourite)
                }
            }

            is HomeContract.Event.ToggleFavourite -> {
                toggleFavorite(event.index)
            }

            HomeContract.Event.Search ->{
                viewModelScope.launch {
                    _effect.emit(HomeContract.Effect.Nav.Search)
                }
            }
        }
    }

    init {
        refreshPage()
        getBlogList()
        getCategory()
    }

    private fun getCategory() {
        viewModelScope.launch {
            when (val res = getCateUS.invoke()) {
                is XResource.Error -> {
                    _state.value = _state.value.copy(
                        categoryState = emptyList()
                    )
                    Log.d("ErrorCategory", "getCategory:${res.error}")
                }

                is XResource.Success -> {
                    _state.value = _state.value.copy(
                        categoryState = res.data
                    )
                }
            }
        }
    }

    private fun isLoading(isLoad: Boolean) {
        _state.value = _state.value.copy(
            isLoading = isLoad
        )
    }

    private fun getBlogList() {
        viewModelScope.launch {
            val res = getBlogListUS.invoke()

            when (res) {
                is XResource.Error -> {
                    isLoading(true)
                    Log.d("err", "getBlogList:${res.error}")
                    when (res.error) {
                        ErrorType.Api.Network -> {
                            _effect.emit(HomeContract.Effect.Nav.ShowError(MessageError.NETWORK_ERROR))
                        }

                        ErrorType.Api.NotFound -> {
                            _effect.emit(HomeContract.Effect.Nav.ShowError(MessageError.ERROR_NOT_FOUND))
                        }

                        ErrorType.Api.Server -> {
                            _effect.emit(HomeContract.Effect.Nav.ShowError(MessageError.SERVER_ERROR))
                        }

                        ErrorType.Api.ServiceUnavailable -> {
                            _effect.emit(HomeContract.Effect.Nav.ShowError(MessageError.SERVICE_UNAVAILABLE))
                        }

                        ErrorType.Unknown -> {
                            _effect.emit(HomeContract.Effect.Nav.ShowError(MessageError.UNKNOWN_ERROR))
                        }
                    }
                }

                is XResource.Success -> {
                    isLoading(false)
                    _state.value = _state.value.copy(
                        homeBlogState = res.data.toMutableStateList(),

                        )


                }
            }

            Log.d("datat", "getBlogList:${res}")
        }
    }

    private fun getSlider() {
        viewModelScope.launch {
            when (val resp = getSliderUS.invoke()) {
                is XResource.Error -> {
                    Log.d("errorSlider", "getBlogList:${resp.error}")
                    _state.value = _state.value.copy(
                        pagerState = PagerData(image = listOf())
                    )
                }

                is XResource.Success -> {
                    _state.value = _state.value.copy(
                        pagerState = resp.data.toUiPager()
                    )
                }
            }
        }
    }

    private fun refreshPage() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                refreshPage = true
            )
            delay(0.5.seconds)
            getSlider()
            getBlogList()

            _state.value = _state.value.copy(refreshPage = false)

        }
    }

    private fun toggleFavorite(index:Int){
        val healthList = _state.value.homeBlogState
        val oldHealthBlog = healthList[index]
        val newHealthBlog = oldHealthBlog.copy(
            favorite = !oldHealthBlog.favorite
        )
        healthList[index]= newHealthBlog
        viewModelScope.launch {
            when(val res = toggleFav.invoke(newHealthBlog.id)){
                is XResource.Error -> {
                    Log.d(" Error Fav", "toggleFavorite: ${res.error}")
                }
                is XResource.Success -> {
                    Log.d("Succes Fav", "toggleFavorite: ${res.data}")
                }
            }
        }
    }


}


private fun InfoSliderModel.toUiPager() = PagerData(
    image = this.slides
)
