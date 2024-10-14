package com.example.kilohealth.feature.feature_home.data.repository

import android.util.Log
import com.example.kilohealth.feature.feature_home.data.datasource.HomeDatasource
import com.example.kilohealth.feature.feature_home.data.mapper.toBlogListModel
import com.example.kilohealth.feature.feature_home.data.mapper.toDetailBlogModel
import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel
import com.example.kilohealth.feature.feature_home.domain.model.DetailBlogModel

import com.example.kilohealth.feature.feature_home.domain.repository.HomeRepository
import org.koin.core.annotation.Factory

@Factory
class HomeRepositoryImpl(
    private val homeDS : HomeDatasource
): HomeRepository {
    override suspend fun getBlogList(): List<BlogListModel> {
        return homeDS.getBlogList().data.toBlogListModel()
    }

    override suspend fun getDetailBlog(id: Int): DetailBlogModel {
        Log.d("repoid", "getDetailBlog: $id")
        return homeDS.getDetailBlog(id).data.toDetailBlogModel()
    }
}