package com.example.kilohealth.feature.feature_home.data.mapper

import com.example.kilohealth.feature.feature_home.data.response.CategoryListResponse
import com.example.kilohealth.feature.feature_home.domain.model.CategoryListModel

fun List<CategoryListResponse>?.toCategoryModel()= this?.map {
    CategoryListModel(
        icon = it.icon ?: "",
        id = it.id ?: "",
        name = it.name ?:" ",
        order = it.order ?: ""
    )
}.orEmpty()