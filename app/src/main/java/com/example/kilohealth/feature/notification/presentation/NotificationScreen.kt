package com.example.kilohealth.feature.notification.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kilohealth.R
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XFontSize
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NotificationScreen() {
    Scaffold(
        topBar = {

            TopAppBar(

                title = {
                    XText(

                        text = "Notifications",
                        fontSize = XFontSize.Large,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = XPadding.extraLarge),
                        )
                },
                colors = TopAppBarColors(
                    containerColor = healthTheme,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    scrolledContainerColor = Color.Transparent,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    XText(
                        text = "Clear All",
                        fontSize = XFontSize.Large,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(end = XPadding.extraLarge),
                    )
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        modifier = Modifier.padding(start = XPadding.extraLarge),
                        contentDescription = null
                    )
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .navigationBarsPadding()
                .fillMaxSize()
        ) {
            items(10) {
                Box(
                    modifier = Modifier.padding(
                        horizontal = XPadding.extraLarge,
                        vertical = XPadding.large
                    )
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.ic_health),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .size(50.dp)

                        )
                        Spacer(modifier = Modifier.width(XPadding.large))
                        Column {
                            XText(text = "Peer learning works. By building robust")
                            Spacer(modifier = Modifier.height(XPadding.large))
                            XText(
                                text = "Aug 12, 2020 at 12:08 PM",
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }

                    }
                }
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondary)
                        .height(1.dp)
                        .fillMaxWidth()

                ) {

                }
            }
        }
    }
}