package com.example.kilohealth.feature.favourite.domain.model

data class FavouriteListModel (
    val id: Int ,
    val type: String,
    val name: String ,
    val description: String,
    val thumbnail: String,
    val favorite: Boolean,
    val createdAt: String,
    val savedAt: String
)