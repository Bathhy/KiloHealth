package com.example.kilohealth.feature.feature_home.domain.usecase

import com.example.kilohealth.feature.feature_home.domain.model.CategoryListModel
import com.example.kilohealth.feature.feature_home.domain.repository.HomeRepository
import com.example.kilohealth.networkconfig.XResource
import org.koin.core.annotation.Single


@Single
class GetCategoryListUseCase (
    private val homeRepository: HomeRepository
){
    suspend fun invoke(): XResource<List<CategoryListModel>>{
        return homeRepository.getCategoryList()
    }
}