package com.example.kilohealth.x_component

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder

@Composable
 fun XImageNetwork(
    modifier: Modifier = Modifier,
    url: String,
    @DrawableRes placeholder: Int? = null,
    @DrawableRes error: Int? = null,
    contentScale: ContentScale = ContentScale.Fit,
) {

    val context = LocalContext.current
    val model = remember(url) {
        val builder = ImageRequest.Builder(context)
            .data(url)
            .diskCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)

        if (placeholder != null) builder.placeholder(placeholder)
        if (error != null) builder.error(error)

        builder.build()
    }
    AsyncImage(
        model = model,
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
    )
}