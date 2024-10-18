package com.example.kilohealth.feature.favourite.data.response

data class FavouriteListResponse (
    val id: Int? ,
    val type: String? ,
    val name: String? ,
    val description: String?,
    val thumbnail: String? ,
    val favorite: Boolean? ,
    val createdAt: String? ,
    val savedAt: String?
)

data class Paging (
    val page: Long? = null,
    val size: Long? = null,
    val total: Long? = null,
    val totalPages: Long? = null
)