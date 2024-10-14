package com.example.kilohealth.feature.feature_home.data.mapper

import com.example.kilohealth.feature.feature_home.data.response.BlogListResponse
import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel


fun List<BlogListResponse>?.toBlogListModel() =this?.map {
    BlogListModel(
        id = it.id ?: 0,
        type = null ?: "",
        name = it.name ?: "Default",
        description = it.description ?: "No Desciption",
        thumbnail = it.thumbnail ?: "none",
        favorite = it.favorite ?: false,
        createdAt = it.createdAt ?: ""
    )
}.orEmpty()