package com.example.kilohealth.feature.favourite.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kilohealth.R
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XFontSize
import com.example.kilohealth.x_component.XIcon
import com.example.kilohealth.x_component.XImageNetwork
import com.example.kilohealth.x_component.XLazyColumn
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    setEvent: (FavouriteContract.Event) -> Unit,
    uiState: FavouriteContract.State
) {
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
                    XIcon(
                        icon = R.drawable.ic_back,
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            setEvent(FavouriteContract.Event.Back)
                        })
                }
            )
        }
    ) {
        XLazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(uiState.favState.size) { favIndex ->
                val favItem = uiState.favState[favIndex]
                Box(
                    contentAlignment = Alignment.TopEnd
                ) {

                    Column(
                        modifier = Modifier
                            .padding(vertical = XPadding.medium)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .fillMaxWidth()
                    ) {
                        XImageNetwork(
                            error = R.drawable.health,
                            url = favItem.thumbnail,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier

                                .fillMaxWidth()
                                .height(150.dp)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    border = BorderStroke(
                                        0.5.dp,
                                        Color.Black
                                    ),
                                    shape = RoundedCornerShape(
                                        bottomEnd = 10.dp,
                                        bottomStart = 10.dp
                                    )
                                )
                        ) {
                            Spacer(modifier = Modifier.height(XPadding.extraSmall))
                            XText(
                                text = favItem.name,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = XPadding.extraLarge)
                            )
                            XText(
                                text = favItem.description,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = XPadding.extraLarge)
                            )

                        }

                    }

                    XIcon(
                        icon = when (favItem.favorite) {
                            true -> {
                                Icons.Default.Favorite
                            }

                            false -> {
                                Icons.Default.FavoriteBorder
                            }
                        },
                        tint = when (favItem.favorite) {
                            true -> {
                                healthTheme
                            }

                            false -> {
                                Color.Unspecified
                            }
                        },
                        modifier = Modifier
                            .padding(XPadding.medium)
                            .size(XPadding.extraLarge * 2)
                            .clickable {
                                setEvent(FavouriteContract.Event.RemoveFavorite(favItem.id))
                            }
                    )
                }

            }
        }
    }
}