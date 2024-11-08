package com.example.kilohealth.feature.feature_home.domain.repository

import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel
import com.example.kilohealth.feature.feature_home.domain.model.DetailBlogModel
import com.example.kilohealth.feature.feature_home.domain.model.InfoSliderModel
import com.example.kilohealth.networkconfig.XResource

interface HomeRepository{
    suspend fun getBlogList(): XResource<List<BlogListModel>>
    suspend fun getDetailBlog(id: Int): XResource<DetailBlogModel>
    suspend fun getInfo(): XResource<InfoSliderModel>
}