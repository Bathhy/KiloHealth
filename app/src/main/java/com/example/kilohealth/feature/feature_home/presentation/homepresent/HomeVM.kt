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
    }
    private fun getBlogList(){
        viewModelScope.launch {
            val res = getBlogListUS.invoke()
            val resp = getSliderUS.invoke()
           when(res){
               is Resource.Error -> {
                   Log.d("err", "getBlogList:${res.error}")
               }
               is Resource.Success -> {
                   Log.d("Slider", "getBlogList:${resp.slides}")
                   _state.value = _state.value.copy(
                       homeBlogState = res.data,
                       pagerState = resp.toUiPager()
                   )
               }
           }
            Log.d("datat", "getBlogList:${res}")
        }
    }

}

private fun InfoSliderModel.toUiPager() = PagerData(
    image = this.slides
)