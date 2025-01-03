package com.example.kilohealth.x_component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.kilohealth.ui.theme.healthTheme

@Composable
internal fun XIcon(
    icon: Any,
    tint:Color = healthTheme,
    modifier: Modifier = Modifier
){
    when(icon){
        is ImageVector -> {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint,
                modifier = modifier
            )
        }
        is Int -> {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = tint,
                modifier = modifier
            )
        }
        else -> throw IllegalArgumentException("Icon not support")
    }
}