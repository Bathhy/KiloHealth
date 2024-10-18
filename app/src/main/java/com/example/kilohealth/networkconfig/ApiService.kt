package com.example.kilohealth.networkconfig

import com.example.kilohealth.feature.favourite.data.datasource.FavouriteEndPoint
import com.example.kilohealth.feature.favourite.data.response.FavouriteListResponse
import com.example.kilohealth.feature.feature_home.data.datasource.HomeEndPoint
import com.example.kilohealth.feature.feature_home.data.response.BlogListResponse
import com.example.kilohealth.feature.feature_home.data.response.DetailBlogResponse
import com.example.kilohealth.feature.feature_home.data.response.InfoSliderResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(HomeEndPoint.BLOG_LIST_ENDPOINT)
    suspend fun getBlogList(): BaseResponse<List<BlogListResponse>?>

    @GET("${HomeEndPoint.BLOG_LIST_ENDPOINT}/{id}")
    suspend fun getDetailBlog(@Path("id") id: Int): BaseResponse<DetailBlogResponse?>

    @GET(HomeEndPoint.INFO_ENDPOINT)
    suspend fun getInfo(): BaseResponse<InfoSliderResponse?>

    @GET(FavouriteEndPoint.Fav_ENDPOINT)
    suspend fun getFavList(): BaseResponse<List<FavouriteListResponse>?>
}