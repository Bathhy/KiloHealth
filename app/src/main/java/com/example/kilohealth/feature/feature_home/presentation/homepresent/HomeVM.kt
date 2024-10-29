package com.example.kilohealth.feature.feature_home.presentation.homepresent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kilohealth.data.PagerData
import com.example.kilohealth.feature.feature_home.domain.model.InfoSliderModel
import com.example.kilohealth.feature.feature_home.domain.usecase.GetBlogListUseCase
import com.example.kilohealth.feature.feature_home.domain.usecase.GetSliderInfo
import com.example.kilohealth.networkconfig.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeVM(
    private val getBlogListUS: GetBlogListUseCase,
    private val getSliderUS: GetSliderInfo
): ViewModel() {
    private val _state = MutableStateFlow(HomeContract.State())
    val state = _state.asStateFlow()
    private val _effect = MutableSharedFlow<HomeContract.Effect>()
    val effect: SharedFlow<HomeContract.Effect> = _effect.asSharedFlow()
    fun onEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.detail -> {
                viewModelScope.launch {
                    _effect.emit(HomeContract.Effect.detail(event.id))
                }
            }
        }
    }

    init {
        getBlogList()
        getSlider()
    }
    private fun isLoading(isLoad:Boolean){
        _state.value = _state.value.copy(
            isLoading = isLoad
        )
    }
    private fun getBlogList(){
        viewModelScope.launch {
            val res = getBlogListUS.invoke()

           when(res){
               is Resource.Error -> {
                   isLoading(true)
                   Log.d("err", "getBlogList:${res.error}")
               }
               is Resource.Success -> {
                   isLoading(false)
                   _state.value = _state.value.copy(
                       homeBlogState = res.data,

                   )

               }
           }

            Log.d("datat", "getBlogList:${res}")
        }
    }
    private fun getSlider(){
        viewModelScope.launch {
            when(val resp = getSliderUS.invoke()){
                is Resource.Error -> {
                    Log.d("errorSlider", "getBlogList:${resp.error}")
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        pagerState = resp.data.toUiPager()
                    )
                }
            }
        }
    }

}

private fun InfoSliderModel.toUiPager() = PagerData(
    image = this.slides
)