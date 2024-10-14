package com.example.kilohealth.feature.feature_home.domain.usecase

import android.util.Log
import com.example.kilohealth.feature.feature_home.domain.model.DetailBlogModel
import com.example.kilohealth.feature.feature_home.domain.repository.HomeRepository
import org.koin.core.annotation.Single

@Single
class GetDetailBlogUseCase(
    private val homeRepo: HomeRepository
){
    suspend fun invoke(id: Int) : DetailBlogModel{
        Log.d("usID", "invoke:${id}")
        return homeRepo.getDetailBlog(id)
    }
}