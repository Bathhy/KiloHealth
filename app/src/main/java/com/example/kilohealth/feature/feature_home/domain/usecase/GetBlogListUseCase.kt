package com.example.kilohealth.feature.feature_home.domain.usecase

import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel
import com.example.kilohealth.feature.feature_home.domain.repository.HomeDataRepository
import org.koin.core.annotation.Single


@Single
class GetBlogListUseCase (
    private val homeRepo: HomeDataRepository
){
    suspend fun invoke(): List<BlogListModel> {
        return homeRepo.getBlogList()
    }
}