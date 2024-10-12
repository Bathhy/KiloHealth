package com.example.kilohealth.networkconfig


data class BaseResponse<T>(
    val status: Int? = null,
    val message: String? = null,
    val data : T? = null,
    val paging: Paging? = null
)
data class Paging(
    val page: Int,
    val size: Int,
    val total: Int,
    val totalPages: Int
)