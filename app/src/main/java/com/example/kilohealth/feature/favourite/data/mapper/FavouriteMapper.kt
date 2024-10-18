package com.example.kilohealth.feature.favourite.data.mapper

import com.example.kilohealth.feature.favourite.data.response.FavouriteListResponse
import com.example.kilohealth.feature.favourite.domain.model.FavouriteListModel

fun List<FavouriteListResponse>?.toFavouriteListModel()= this?.map {
    FavouriteListModel(
        id = it.id ?: 0,
        type = it.type ?:" ",
        name = it.name ?: "",
        description = it.description ?:"",
        thumbnail = it.thumbnail ?: "",
        favorite = it.favorite ?: false,
        createdAt = it.createdAt ?: "",
        savedAt = it.savedAt ?: ""
    )
}.orEmpty()