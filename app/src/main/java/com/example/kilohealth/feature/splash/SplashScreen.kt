package com.example.kilohealth.feature.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.kilohealth.R
import com.example.kilohealth.navigation.Route
import com.example.kilohealth.navigation.Screen

internal fun NavGraphBuilder.toSplashNav(navHostController: NavHostController)=composable(
    route = Screen.Splash.route,
    content = {
        SplashScreen(navHostController)
    }
)

@Composable
internal fun SplashScreen(
    navHostController: NavHostController
) {
    val isAnimating = remember {
        Animatable(0.0f)
    }
//    val scaleImg  by animateFloatAsState(
//        targetValue = if(isAnimating.value) 1f else 0.5f,
//        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
//    )
    LaunchedEffect(Unit) {
        isAnimating.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 800 , easing = FastOutLinearInEasing)
        )
        navHostController.navigate(Route.HEALTH)
//        isAnimating.animateTo(
//            targetValue = 1f,
//            animationSpec = tween(800, easing = {
//                OvershootInterpolator(4f).getInterpolation(it)
//            })
//        )

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
        ,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_health),
            modifier = Modifier
                .size(200.dp)

                .scale(isAnimating.value),

            contentDescription = null
        )
    }
}