package com.example.kilohealth.feature.feature_home.domain.usecase

import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel
import com.example.kilohealth.feature.feature_home.domain.repository.HomeRepository
import com.example.kilohealth.networkconfig.Resource
import com.example.kilohealth.networkconfig.apiHandler
import org.koin.core.annotation.Single


@Single
class GetBlogListUseCase (
    private val homeRepo: HomeRepository
){
    suspend fun invoke(): Resource<List<BlogListModel>> {
        return apiHandler {
            homeRepo.getBlogList()
        }
    }
}