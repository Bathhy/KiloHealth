package com.example.kilohealth.feature.profile.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
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
import com.example.kilohealth.data.FakeData
import com.example.kilohealth.x_component.XFontSize
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XText

@Composable
internal fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_health),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(100.dp)

        )
        Spacer(modifier = Modifier.height(XPadding.medium))
        XText(
            text = "Nigga",
            fontWeight = FontWeight.Bold,
            fontSize = XFontSize.ExtraLarge
        )
        Spacer(modifier = Modifier.height(XPadding.large))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = XPadding.extraLarge),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            repeat(FakeData.healthStatList.size) {
                val healthStat = FakeData.healthStatList[it]
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = healthStat.icon),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(50.dp),
                    )
                    XText(
                        text = healthStat.label,
                        color = Color.Blue,
                        fontWeight = FontWeight.Medium,
                        fontSize = XFontSize.Medium,
                    )
                    XText(
                        text = healthStat.healthNumStat,
                        fontSize = XFontSize.Large,
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(XPadding.extraLarge))
        repeat(6) {
            Column(
                modifier = Modifier
                    .padding(horizontal = XPadding.extraLarge, vertical = XPadding.large)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_heart_rate),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp),
                        )
                        Spacer(modifier = Modifier.width(XPadding.large))
                        XText(text = "My Saved", fontSize = XFontSize.Large)
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_forward),
                        contentDescription = null,
                    )
                }
                Box(
                    modifier = Modifier

                        .background(Color.LightGray)
                        .height(1.dp)
                        .fillMaxWidth()

                ) {

                }
            }
        }
    }
}