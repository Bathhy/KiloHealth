package com.example.kilohealth.feature.feature_home.domain.model

data class DetailBlogModel (
    val id: Int ,
    val type: String ,
    val name: String ,
    val description: String ,
    val content: String,
    val thumbnail: String,
    val views: Long,
    val status: String,
    val favorite: Boolean,
    val createdAt: String,
    val categories: List<Category>,
    val tags: List<Tag>
)

data class Category (
    val id: Int,
    val name: String,
    val icon: String,
    val order: Long
)

data class Tag (
    val id: Int,
    val name: String
)