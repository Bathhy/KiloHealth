package com.example.kilohealth.feature.feature_home.data.datasource

import com.example.kilohealth.feature.feature_home.data.response.BlogListResponse
import com.example.kilohealth.networkconfig.ApiService
import com.example.kilohealth.networkconfig.BaseResponse
import org.koin.core.annotation.Single

@Single
class HomeDataSourceImpl(
    private val api : ApiService
): HomeDatasource{
    override suspend fun getBlogList(): BaseResponse<List<BlogListResponse>?> {
        return api.getBlogList()
    }

}