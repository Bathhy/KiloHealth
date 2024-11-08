package com.example.kilohealth.feature.feature_home.data.datasource

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kilohealth.feature.feature_home.data.response.BlogListResponse
import com.example.kilohealth.feature.feature_home.data.response.DetailBlogResponse
import com.example.kilohealth.feature.feature_home.data.response.InfoSliderResponse
import com.example.kilohealth.networkconfig.ApiService
import com.example.kilohealth.networkconfig.BaseResponse
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single
import kotlin.math.truncate

@Single
class HomeDataSourceImpl(
    private val api : ApiService
): HomeDatasource{
    override suspend fun getBlogList(): BaseResponse<List<BlogListResponse>?> {
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
        return api.getBlogList()
    }

    override suspend fun getDetailBlog(id: Int): BaseResponse<DetailBlogResponse?> {
        Log.d("homDS", "id:$id ")
        return api.getDetailBlog(id)
    }

    override suspend fun getSlider(): BaseResponse<InfoSliderResponse?> {
        return api.getInfo()
    }

}