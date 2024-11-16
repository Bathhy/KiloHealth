package com.example.kilohealth.feature.feature_home.domain.usecase

import com.example.kilohealth.feature.feature_home.domain.repository.HomeRepository
import com.example.kilohealth.networkconfig.XResource
import org.koin.core.annotation.Single


@Single
class ToggleFavoriteUseCase(
    private val homeRepository: HomeRepository
) {
    suspend fun invoke(id:Int): XResource<String>{
        return homeRepository.toggleFavorite(id)
    }
}