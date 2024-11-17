package com.example.kilohealth.feature.feature_home.data.local

import com.example.kilohealth.feature.feature_home.data.local.entity.BlogListEntity

interface HomeLocalDataSource{
    suspend fun getBlogList(): List<BlogListEntity>
    suspend fun saveBlogList(blog:List<BlogListEntity>)
    suspend fun updateBlogList(blog:List<BlogListEntity>)
    suspend fun syncDataBlog(blog:List<BlogListEntity>)
}