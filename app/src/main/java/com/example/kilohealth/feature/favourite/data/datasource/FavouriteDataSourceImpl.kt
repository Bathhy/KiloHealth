package com.example.kilohealth.feature.favourite.data.datasource

import com.example.kilohealth.feature.favourite.data.response.FavouriteListResponse
import com.example.kilohealth.networkconfig.ApiService
import com.example.kilohealth.networkconfig.BaseResponse
import org.koin.core.annotation.Single

@Single
class FavouriteDataSourceImpl(
    private val apiClient: ApiService
): FavouriteDataSource {
    override suspend fun getFavList(): BaseResponse<List<FavouriteListResponse>?> {
        return apiClient.getFavList()
    }
}