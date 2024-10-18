package com.example.kilohealth.feature.favourite.domain.usecase

import com.example.kilohealth.feature.favourite.domain.model.FavouriteListModel
import com.example.kilohealth.feature.favourite.domain.repository.FavouriteRepository
import com.example.kilohealth.networkconfig.Resource
import com.example.kilohealth.networkconfig.apiHandler
import org.koin.core.annotation.Single

@Single
class GetFavouriteListUseCase (
    private val favRepo: FavouriteRepository
){
    suspend fun invoke(): Resource<List<FavouriteListModel>>{

        return apiHandler {
            favRepo.getFavList()
        }
    }
}