package com.example.kilohealth.feature.favourite.data.repository

import com.example.kilohealth.feature.favourite.data.datasource.FavouriteDataSource
import com.example.kilohealth.feature.favourite.data.mapper.toFavouriteListModel
import com.example.kilohealth.feature.favourite.domain.model.FavouriteListModel
import com.example.kilohealth.feature.favourite.domain.repository.FavouriteRepository
import org.koin.core.annotation.Factory

@Factory
class FavouriteRepositoryImpl(
    private val favDS: FavouriteDataSource
) : FavouriteRepository {
    override suspend fun getFavList(): List<FavouriteListModel> {
        return favDS.getFavList().data.toFavouriteListModel()
    }
}