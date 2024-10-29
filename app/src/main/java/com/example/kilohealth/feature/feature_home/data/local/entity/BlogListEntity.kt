package com.example.kilohealth.feature.feature_home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Blog")
data class BlogListEntity(

    @PrimaryKey(autoGenerate = true) val blogId: Int = 0,
    val title: String,
    val description : String,
    val image: String
)