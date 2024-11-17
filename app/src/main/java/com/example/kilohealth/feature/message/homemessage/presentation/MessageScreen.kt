package com.example.kilohealth.feature.message.homemessage.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kilohealth.R
import com.example.kilohealth.x_component.XFontSize
import com.example.kilohealth.x_component.XLazyColumn
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XText
import com.example.kilohealth.x_component.XTopBar


@Composable
fun MessageScreen(
    setEvent: (MessageContract.Event) -> Unit
) {
    Scaffold(
        topBar = {
            XTopBar()
        }
    ) {
        XLazyColumn(
            modifier = Modifier
                .padding(it)

//                .navigationBarsPadding()
        ) {
            item {
                XText(
                    text = "Emergency consult with your recommended doctor",
                    fontSize = XFontSize.ExtraLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(XPadding.extraLarge))
                LazyRow {
                    items(10) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_health),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(horizontal = XPadding.extraSmall)
                                .clip(shape = CircleShape)
                                .size(60.dp)

                        )
                    }
                }
                Spacer(modifier = Modifier.height(XPadding.extraLarge))
                XText(
                    text = "All Message",
                    fontSize = XFontSize.Large,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(XPadding.extraLarge))

            }
            items(10) {
                Box(
                    modifier = Modifier
                        .clickable {
                            setEvent(MessageContract.Event.goToDeTailMessage)
                        }
                        .padding(vertical = XPadding.extraSmall)
                        .clip(shape = RoundedCornerShape(XPadding.large))
                        .background(Color.LightGray)
                        .padding(XPadding.extraLarge)
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_health),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(horizontal = XPadding.extraSmall)
                                .clip(shape = CircleShape)
                                .size(60.dp)

                        )
                        Spacer(modifier = Modifier.width(XPadding.large))
                        Column {
                            XText(text = "Dr Leonard", fontWeight = FontWeight.Bold)
                            Row() {
                                XText(text = "you:Hello Sir")
                                XText(text = ".Justnow")
                            }

                        }
                    }
                }
            }
        }
    }
}

