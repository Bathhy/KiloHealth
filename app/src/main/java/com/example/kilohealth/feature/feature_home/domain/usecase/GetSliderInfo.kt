package com.example.kilohealth.feature.feature_home.domain.usecase

import com.example.kilohealth.feature.feature_home.domain.model.InfoSliderModel
import com.example.kilohealth.feature.feature_home.domain.repository.HomeRepository
import com.example.kilohealth.networkconfig.XResource
import org.koin.core.annotation.Single


@Single
class GetSliderInfo (
    private val homeRepo: HomeRepository
){
    suspend fun invoke(): XResource<InfoSliderModel>{
        return homeRepo.getInfo()
    }
}