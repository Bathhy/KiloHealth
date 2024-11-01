package com.example.kilohealth.feature.feature_home.data.repository

import android.util.Log
import com.example.kilohealth.feature.feature_home.data.datasource.HomeDatasource
import com.example.kilohealth.feature.feature_home.data.local.HomeLocalDataSource
import com.example.kilohealth.feature.feature_home.data.mapper.toBlogListDomainModel
import com.example.kilohealth.feature.feature_home.data.mapper.toBlogListModel
import com.example.kilohealth.feature.feature_home.data.mapper.toDetailBlogModel
import com.example.kilohealth.feature.feature_home.data.mapper.toInfoSliderModel
import com.example.kilohealth.feature.feature_home.data.mapper.toLocalBlogListModel
import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel
import com.example.kilohealth.feature.feature_home.domain.model.DetailBlogModel
import com.example.kilohealth.feature.feature_home.domain.model.InfoSliderModel

import com.example.kilohealth.feature.feature_home.domain.repository.HomeRepository
import com.example.kilohealth.networkconfig.NetworkChecker
import com.example.kilohealth.networkconfig.Resource
import com.example.kilohealth.networkconfig.apiHandler
import org.koin.core.annotation.Factory

@Factory
class HomeRepositoryImpl(
    private val homeDS: HomeDatasource,
    private val homeLocalDS: HomeLocalDataSource,
    private val networkChecker: NetworkChecker

) : HomeRepository {

    override suspend fun getBlogList(): Resource<List<BlogListModel>> {
        return if (networkChecker.isNetworkAvailable()) {
            val remoteRes = apiHandler {
                homeDS.getBlogList().data.toBlogListModel()
            }
            if(remoteRes is Resource.Success){
                homeLocalDS.syncDataBlog(remoteRes.data.toLocalBlogListModel())
            }
            remoteRes
        }else{
            val localRes = homeLocalDS.getBlogList().toBlogListDomainModel()
            Resource.Success(localRes)
        }
    }

    override suspend fun getDetailBlog(id: Int): Resource<DetailBlogModel> {
        Log.d("repoid", "getDetailBlog: $id")
        return apiHandler {
            homeDS.getDetailBlog(id).data.toDetailBlogModel()
        }
    }

    override suspend fun getInfo(): Resource<InfoSliderModel> {
        return apiHandler {
            homeDS.getSlider().data.toInfoSliderModel()
        }
    }
}