package com.example.kilohealth.networkconfig

import com.example.kilohealth.feature.feature_home.data.datasource.HomeEndPoint
import com.example.kilohealth.feature.feature_home.data.response.BlogListResponse
import retrofit2.http.GET

interface ApiService {
    @GET(HomeEndPoint.BLOG_LIST_ENDPOINT)
    suspend fun getBlogList(): BaseResponse<List<BlogListResponse>?>
}