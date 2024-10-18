package com.example.kilohealth.feature.feature_home.domain.repository

import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel
import com.example.kilohealth.feature.feature_home.domain.model.DetailBlogModel
import com.example.kilohealth.feature.feature_home.domain.model.InfoSliderModel

interface HomeRepository{
    suspend fun getBlogList(): List<BlogListModel>
    suspend fun getDetailBlog(id: Int): DetailBlogModel
    suspend fun getInfo(): InfoSliderModel
}