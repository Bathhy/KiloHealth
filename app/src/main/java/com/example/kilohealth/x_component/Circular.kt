package com.example.kilohealth.x_component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kilohealth.ui.theme.healthTheme

@Composable
internal fun XCircular(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(XPadding.extraLarge),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            trackColor = Color.LightGray,
            color = healthTheme,
            modifier = Modifier.size(40.dp),
        )
    }
}
