package com.example.kilohealth.feature.feature_home.data.local

import com.example.kilohealth.feature.feature_home.data.local.dao.BlogListDao
import com.example.kilohealth.feature.feature_home.data.local.entity.BlogListEntity

class HomeLocalDataSourceImpl(
    private val blogDao: BlogListDao
) : HomeLocalDataSource {
    override suspend fun getBlogList(): List<BlogListEntity> {
        return blogDao.getBlogLocal()
    }

    override suspend fun saveBlogList(blog: List<BlogListEntity>) {
       return blogDao.insertBlogLocal(blog)
    }

    override suspend fun updateBlogList(blog: List<BlogListEntity>) {
        return blogDao.updateBlogLocal(blog)
    }

    override suspend fun syncDataBlog(blog: List<BlogListEntity>) {
        val localBlog = getBlogList()
        localBlog.forEach {
                rem->
            val blogExist  = localBlog.any{
                it.blogId == rem.blogId
            }
            if(blogExist){
                updateBlogList(blog)
            }else{
                saveBlogList(blog)
            }
        }

    }


}