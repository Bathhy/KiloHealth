package com.example.kilohealth.feature.favourite.domain.repository

import com.example.kilohealth.feature.favourite.domain.model.FavouriteListModel

interface FavouriteRepository {
    suspend fun getFavList(): List<FavouriteListModel>
}