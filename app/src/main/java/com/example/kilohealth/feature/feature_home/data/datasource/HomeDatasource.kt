package com.example.kilohealth.feature.feature_home.data.datasource

import androidx.paging.PagingData
import com.example.kilohealth.feature.feature_home.data.response.BlogListResponse
import com.example.kilohealth.feature.feature_home.data.response.CategoryListResponse
import com.example.kilohealth.feature.feature_home.data.response.DetailBlogResponse
import com.example.kilohealth.feature.feature_home.data.response.InfoSliderResponse
import com.example.kilohealth.networkconfig.BaseResponse
import kotlinx.coroutines.flow.Flow

interface HomeDatasource {
    suspend fun getBlogList(value:String?=null): BaseResponse<List<BlogListResponse>?>
    suspend fun getDetailBlog(id:Int): BaseResponse<DetailBlogResponse?>
    suspend fun getSlider(): BaseResponse<InfoSliderResponse?>
    suspend fun getCategoryList(): BaseResponse<List<CategoryListResponse>?>
    suspend fun toggleFavorite(id:Int): BaseResponse<String>
}