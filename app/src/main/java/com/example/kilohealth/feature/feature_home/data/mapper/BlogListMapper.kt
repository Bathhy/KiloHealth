package com.example.kilohealth.feature.feature_home.data.mapper

import com.example.kilohealth.feature.feature_home.data.response.BlogListResponse
import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel


fun List<BlogListResponse>?.toBlogListModel() =this?.map {
    BlogListModel(
        id = it.id,
        type = null,
        name = it.name,
        description = it.description,
        thumbnail = it.thumbnail,
        favorite = it.favorite,
        createdAt = it.createdAt
    )
}.orEmpty()