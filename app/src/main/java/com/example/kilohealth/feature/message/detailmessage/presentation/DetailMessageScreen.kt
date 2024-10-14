package com.example.kilohealth.feature.message.detailmessage.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.kilohealth.R
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XFontSize
import com.example.kilohealth.x_component.XIcon
import com.example.kilohealth.x_component.XLazyColumn
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XText
import com.example.kilohealth.x_component.XTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMessageScreen(
    setEvent: (DetailMessageContract.Event) -> Unit
) {
    val messaageTFState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    Scaffold(topBar = {
        TopAppBar(title = {
            XText(
                text = "Dr Nigga",
                fontSize = XFontSize.Medium,
                fontWeight = FontWeight.Medium
            )
        }, actions = {
            XIcon(icon = painterResource(id = R.drawable.ic_phone_cal), tint = healthTheme)
            Spacer(modifier = Modifier.width(XPadding.medium))
            XIcon(icon = painterResource(id = R.drawable.ic_video), tint = healthTheme)
        }, navigationIcon = {
            XIcon(icon = Icons.Default.ArrowBack, modifier = Modifier.clickable {
                setEvent(DetailMessageContract.Event.back)
            })
        })

    },
        bottomBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                XIcon(icon = painterResource(id = R.drawable.ic_photo_library), tint = healthTheme)
                Spacer(modifier = Modifier.width(XPadding.large))
                XIcon(icon = painterResource(id = R.drawable.ic_voice), healthTheme)
                XTextField(
                    placeholder = "Enter your Message",
                    textstate = messaageTFState.value,

                    trailingIcon = {
                        XIcon(
                            icon = painterResource(id = R.drawable.ic_send),
                            tint = healthTheme
                        )
                    }
                )
            }
        }
    ) {
        XLazyColumn(
            modifier = Modifier.padding(it)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .padding(XPadding.extraLarge)
                        .fillMaxWidth()

                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.ic_health),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(horizontal = XPadding.extraSmall)
                                .clip(shape = CircleShape)
                                .size(60.dp)

                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            XText(
                                text = "Tue 12.30",
                                fontSize = XFontSize.Small,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(XPadding.extraSmall))
                            Box(
                                modifier = Modifier

                                    .clip(shape = RoundedCornerShape(XPadding.medium))
                                    .background(Color.LightGray)
                                    .fillMaxSize()
                                    .padding(XPadding.extraLarge)

                            ) {
                                XText(text = "Hello nigga, can i Love you")
                            }
                        }
                    }
                }
            }
        }
    }
}