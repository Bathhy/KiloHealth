package com.example.kilohealth.feature.feature_home.domain.model





data class BlogListModel (
    val id: Int?,
    val type: Type?,
    val name: String?,
    val description: String?,
    val thumbnail: String?,
    val favorite: Boolean?,
    val createdAt: String?
)

enum class Type {
    Blog
}

data class Paging (
    val page: Int?,
    val size: Int?,
    val total: Int?,
    val totalPages: Int?
)
