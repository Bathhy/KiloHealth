package com.example.kilohealth.feature.feature_home.data.mapper

import com.example.kilohealth.feature.feature_home.data.response.InfoSliderResponse
import com.example.kilohealth.feature.feature_home.domain.model.InfoSliderModel

fun InfoSliderResponse?.toInfoSliderModel()= InfoSliderModel(
    slides = this?.slides ?: emptyList(),
    name =this?.name ?: "",
    description = this?.description ?: "",
    facebook = this?.facebook ?: "",
    telegram = this?.telegram ?: "",
    tiktok = this?.tiktok ?: "",
    youtube = this?.youtube ?: "",
    email = this?.email ?: "",
    phoneNumbers = this?.phoneNumbers ?: emptyList()
)