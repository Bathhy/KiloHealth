package com.example.kilohealth.feature.feature_home.domain.repository

import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel

interface HomeDataRepository{
    suspend fun getBlogList(): List<BlogListModel>
}