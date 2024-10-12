package com.example.kilohealth.feature.feature_home.data.repository

import com.example.kilohealth.feature.feature_home.data.datasource.HomeDatasource
import com.example.kilohealth.feature.feature_home.data.mapper.toBlogListModel
import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel
import com.example.kilohealth.feature.feature_home.domain.repository.HomeDataRepository
import org.koin.core.annotation.Factory

@Factory
class HomeRepositoryImpl(
    private val homeDS : HomeDatasource
):HomeDataRepository {
    override suspend fun getBlogList(): List<BlogListModel> {
        return homeDS.getBlogList().data.toBlogListModel()
    }
}