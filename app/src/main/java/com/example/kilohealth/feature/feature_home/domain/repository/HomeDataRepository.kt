package com.example.kilohealth.feature.feature_home.domain.repository

import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel
import com.example.kilohealth.feature.feature_home.domain.model.DetailBlogModel
import com.example.kilohealth.feature.feature_home.domain.model.InfoSliderModel
import com.example.kilohealth.networkconfig.Resource

interface HomeRepository{
    suspend fun getBlogList(): Resource<List<BlogListModel>>
    suspend fun getDetailBlog(id: Int): Resource<DetailBlogModel>
    suspend fun getInfo(): Resource<InfoSliderModel>
}