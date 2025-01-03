package com.example.kilohealth.feature.feature_home.data.repository

import android.util.Log
import com.example.kilohealth.feature.feature_home.data.datasource.HomeDatasource
import com.example.kilohealth.feature.feature_home.data.local.HomeLocalDataSource
import com.example.kilohealth.feature.feature_home.data.mapper.toBlogListModel
import com.example.kilohealth.feature.feature_home.data.mapper.toCategoryModel
import com.example.kilohealth.feature.feature_home.data.mapper.toDetailBlogModel
import com.example.kilohealth.feature.feature_home.data.mapper.toInfoSliderModel
import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel
import com.example.kilohealth.feature.feature_home.domain.model.CategoryListModel
import com.example.kilohealth.feature.feature_home.domain.model.DetailBlogModel
import com.example.kilohealth.feature.feature_home.domain.model.InfoSliderModel
import com.example.kilohealth.feature.feature_home.domain.repository.HomeRepository
import com.example.kilohealth.networkconfig.NetworkChecker
import com.example.kilohealth.networkconfig.XResource
import com.example.kilohealth.networkconfig.apiHandler
import org.koin.core.annotation.Factory

@Factory
class HomeRepositoryImpl(
    private val homeDS: HomeDatasource,
    private val homeLocalDS: HomeLocalDataSource,
    private val networkChecker: NetworkChecker

) : HomeRepository {

    override suspend fun getBlogList(value: String?): XResource<List<BlogListModel>> {
//        return if (networkChecker.isNetworkAvailable()) {
//            val remoteRes = apiHandler {
//                homeDS.getBlogList(value = value).data.toBlogListModel()
//            }
//            if(remoteRes is XResource.Success){
//                homeLocalDS.syncDataBlog(remoteRes.data.toLocalBlogListModel())
//            }
//            remoteRes
//        }else{
//            val localRes = homeLocalDS.getBlogList().toBlogListDomainModel()
//            XResource.Success(localRes)
//        }
        return apiHandler {
            homeDS.getBlogList().data.toBlogListModel()
        }
    }

    override suspend fun getDetailBlog(id: Int): XResource<DetailBlogModel> {
        Log.d("repoid", "getDetailBlog: $id")
        return apiHandler {
            homeDS.getDetailBlog(id).data.toDetailBlogModel()
        }
    }

    override suspend fun getInfo(): XResource<InfoSliderModel> {
        return apiHandler {
            homeDS.getSlider().data.toInfoSliderModel()
        }
    }

    override suspend fun getCategoryList(): XResource<List<CategoryListModel>> {
        return apiHandler {
            homeDS.getCategoryList().data.toCategoryModel()
        }
    }

    override suspend fun toggleFavorite(id: Int): XResource<String> {
        return apiHandler {
            homeDS.toggleFavorite(id).data.orEmpty()
        }
    }
}