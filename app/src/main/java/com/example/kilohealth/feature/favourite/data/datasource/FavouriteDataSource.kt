package com.example.kilohealth.feature.favourite.data.datasource

import com.example.kilohealth.feature.favourite.data.response.FavouriteListResponse
import com.example.kilohealth.networkconfig.BaseResponse

interface FavouriteDataSource {
    suspend fun getFavList(): BaseResponse<List<FavouriteListResponse>?>
}