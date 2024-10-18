package com.example.kilohealth.feature.feature_home.data.datasource

import com.example.kilohealth.feature.feature_home.data.response.BlogListResponse
import com.example.kilohealth.feature.feature_home.data.response.DetailBlogResponse
import com.example.kilohealth.feature.feature_home.data.response.InfoSliderResponse
import com.example.kilohealth.networkconfig.BaseResponse

interface HomeDatasource {
    suspend fun getBlogList(): BaseResponse<List<BlogListResponse>?>
    suspend fun getDetailBlog(id:Int): BaseResponse<DetailBlogResponse?>
    suspend fun getSlider(): BaseResponse<InfoSliderResponse?>
}