package com.example.kilohealth.feature.favourite

import android.content.res.Resources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.kilohealth.R
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XFontSize
import com.example.kilohealth.x_component.XLazyColumn
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XText

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen() {
    Scaffold(
        topBar = {

            TopAppBar(

                title = {
                    XText(

                        text = "Favourite",
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
//                    XText(
//                        text = "Clear All",
//                        fontSize = XFontSize.Large,
//                        fontWeight = FontWeight.SemiBold,
//                        modifier = Modifier.padding(end = XPadding.extraLarge),
//                    )
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
        XLazyColumn(
            modifier = Modifier.padding(it)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .background(Color.Green)
                        .fillMaxWidth()
                        .padding(
                            vertical = XPadding.extraLarge
                        )
                ) {
                    Column{
                        AsyncImage(
                            contentDescription = null,
                            model = "https://images.deliveryhero.io/image/fd-kh/LH/t4qx-hero.jpg",
                            contentScale = ContentScale.Crop,

                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
//                                .border(
//                                    border = BorderStroke(
//                                        0.5.dp,
//                                        color
//                                    ),
//                                    shape = RoundedCornerShape(
//                                        bottomEnd = XShape.medium,
//                                        bottomStart = XShape.medium
//                                    )
//                                )
                        ) {
                            Column {
                                Spacer(modifier= Modifier.height(XPadding.extraSmall))
                                XText(
                                    text = "title",
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(horizontal = XPadding.extraLarge)
                                )
                                XText(
                                    text = "From $ ",
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(horizontal = XPadding.extraLarge)
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier.padding(
                            horizontal = XPadding.medium,
                            vertical = XPadding.medium,
                        ), contentAlignment = Alignment.TopEnd
                    ) {
//                        XButtonIcon(
//                            image = xyz.x.component.R.drawable.ic_favourites,
//                            size = XIconSize.Medium,
//
//                            tint = Theme.Color.foodPrimary,
//                        ) {
//                        }
                    }
                }
            }
        }
    }
}