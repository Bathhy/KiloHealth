package com.example.kilohealth.feature.feature_home.domain.model

data class InfoSliderModel (
    val slides: List<String> ,
    val name: String,
    val description: String,
    val facebook: String,
    val telegram: String,
    val tiktok: String,
    val youtube: String,
    val email: String,
    val phoneNumbers: List<String>
)