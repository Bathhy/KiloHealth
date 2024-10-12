package com.example.kilohealth.feature.feature_home.data.datasource

import com.example.kilohealth.feature.feature_home.data.response.BlogListResponse
import com.example.kilohealth.networkconfig.BaseResponse

interface HomeDatasource {
    suspend fun getBlogList(): BaseResponse<List<BlogListResponse>?>
}