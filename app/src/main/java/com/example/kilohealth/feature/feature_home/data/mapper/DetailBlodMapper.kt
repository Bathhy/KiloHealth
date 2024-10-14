package com.example.kilohealth.feature.feature_home.data.mapper

import com.example.kilohealth.feature.feature_home.data.response.DetailBlogResponse
import com.example.kilohealth.feature.feature_home.domain.model.DetailBlogModel

fun DetailBlogResponse?.toDetailBlogModel()= DetailBlogModel(
    id = this?.id ?: 0,
    type = this?.type ?: "",
    name = this?.name ?: "",
    description = this?.description ?: "",
    content =this?.content ?: "" ,
    thumbnail = this?.thumbnail ?: "",
    views = this?.views ?: 0,
    status = this?.status ?: "",
    favorite = this?.favorite ?: false,
    createdAt = this?.createdAt ?: "",
    categories = listOf(),
    tags = listOf()
)

