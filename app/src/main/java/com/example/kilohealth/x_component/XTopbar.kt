package com.example.kilohealth.x_component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kilohealth.R
import com.example.kilohealth.ui.theme.healthTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XTopBar() {
    TopAppBar(
        title = { },
        actions = {
            XIcon(icon = Icons.Default.Notifications, tint = healthTheme)
        },
        navigationIcon = {
            XIcon(
                icon = R.drawable.person_img,
                modifier = Modifier.size(30.dp)
            )
        }
    )
}