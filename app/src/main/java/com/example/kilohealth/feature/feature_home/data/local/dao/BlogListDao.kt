package com.example.kilohealth.feature.feature_home.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.kilohealth.feature.feature_home.data.local.entity.BlogListEntity

@Dao
interface BlogListDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogLocal (blogEntity:List< BlogListEntity>)

    @Query("SELECT * FROM Blog")
    suspend fun getBlogLocal(): List<BlogListEntity>

    @Update
    suspend fun updateBlogLocal(blog:List<BlogListEntity>)

}