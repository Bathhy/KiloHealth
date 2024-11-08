package com.example.kilohealth.networkconfig

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kilohealth.feature.feature_home.data.response.BlogListResponse
import org.koin.core.annotation.Single
import java.io.IOException


//@Single
//class NetworkPagingSource (
//    private val apiService: ApiService
//) : PagingSource<Int, BlogListResponse>(){
//    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BlogListResponse> {
//        return try {
//            val currentPage = params.key ?: 1
//            val res = apiService.getBlogList(page = 10)
//            val blogData = res.data
//
//            LoadResult.Page(
//                data = blogData.orEmpty(),
//                prevKey = if (currentPage == 1) null else currentPage - 1,
//                nextKey = if (blogData.orEmpty().isEmpty()) null else currentPage + 1
//            )
//        } catch (exception: IOException) {
//            return LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            return LoadResult.Error(exception)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, BlogListResponse>): Int? {
//        return state.anchorPosition
//    }
//}
