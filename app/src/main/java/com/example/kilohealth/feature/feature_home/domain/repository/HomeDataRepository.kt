package com.example.kilohealth.feature.feature_home.domain.repository

import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel
import com.example.kilohealth.feature.feature_home.domain.model.DetailBlogModel

interface HomeRepository{
    suspend fun getBlogList(): List<BlogListModel>
    suspend fun getDetailBlog(id: Int): DetailBlogModel
}