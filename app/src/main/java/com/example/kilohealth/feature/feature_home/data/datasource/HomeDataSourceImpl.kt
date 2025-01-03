package com.example.kilohealth.feature.feature_home.data.datasource

import android.util.Log
import com.example.kilohealth.feature.feature_home.data.response.BlogListResponse
import com.example.kilohealth.feature.feature_home.data.response.CategoryListResponse
import com.example.kilohealth.feature.feature_home.data.response.DetailBlogResponse
import com.example.kilohealth.feature.feature_home.data.response.InfoSliderResponse
import com.example.kilohealth.networkconfig.ApiService
import com.example.kilohealth.networkconfig.BaseResponse
import org.koin.core.annotation.Single

@Single
class HomeDataSourceImpl(
    private val api: ApiService
) : HomeDatasource {
    override suspend fun getBlogList(value: String?): BaseResponse<List<BlogListResponse>?> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = page,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = {
////                api.getBlogList(page)
//                NetworkPagingSource(this)
//            }
//        ).flow
        return api.getBlogList(value = value)
    }

    override suspend fun getDetailBlog(id: Int): BaseResponse<DetailBlogResponse?> {
        Log.d("homDS", "id:$id ")
        return api.getDetailBlog(id)
    }

    override suspend fun getSlider(): BaseResponse<InfoSliderResponse?> {
        return api.getInfo()
    }

    override suspend fun getCategoryList(): BaseResponse<List<CategoryListResponse>?> {
        return api.getCategoryList()
    }

    override suspend fun toggleFavorite(id: Int): BaseResponse<String> {
        return api.toggleFavorite(id)
    }

}