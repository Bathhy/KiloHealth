package com.example.kilohealth.feature.favourite.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kilohealth.feature.favourite.domain.usecase.GetFavouriteListUseCase
import com.example.kilohealth.feature.feature_home.domain.usecase.ToggleFavoriteUseCase
import com.example.kilohealth.networkconfig.XResource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class FavouriteVM(
    private val getFavUS: GetFavouriteListUseCase,
    private val toggleFavUS: ToggleFavoriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(FavouriteContract.State())
    val state = _state.asStateFlow()
    private val _effect = MutableSharedFlow<FavouriteContract.Effect>()
    val effect: SharedFlow<FavouriteContract.Effect> = _effect.asSharedFlow()
    fun onEvent(
        event: FavouriteContract.Event
    ) {
        when (event) {
            FavouriteContract.Event.Back -> {
                viewModelScope.launch {
                    _effect.emit(FavouriteContract.Effect.Nav.Back)
                }
            }

            is FavouriteContract.Event.RemoveFavorite -> TODO()
        }
    }

    init {
        getFavouriteList()
    }

    private fun getFavouriteList() {
        viewModelScope.launch {
            when (val res = getFavUS.invoke()) {
                is XResource.Error -> {
                    Log.d("errFav", "getFavouriteList:${res.error}")
                }

                is XResource.Success -> {
                    Log.d("succeFav", "getFavouriteList:${res.data}")
                    _state.value = _state.value.copy(
                        favState = res.data.toMutableList()
                    )
                }
            }
        }
    }

    private fun removeFavourite(merchantId:Int){
        val listFav =_state.value.favState.toMutableList()
        val index = listFav.indexOfFirst {
            it.id == merchantId
        }
        if(index != -1){
            listFav.removeAt(index)
            _state.value = _state.value.copy(
                favState = listFav
            )
        }
        viewModelScope.launch {
            val res = toggleFavUS.invoke(merchantId)

        }
    }
}