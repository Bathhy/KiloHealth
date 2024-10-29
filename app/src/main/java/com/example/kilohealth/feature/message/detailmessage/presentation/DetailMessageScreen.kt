package com.example.kilohealth.feature.message.detailmessage.presentation

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.kilohealth.R
import com.example.kilohealth.data.FakeData
import com.example.kilohealth.data.Message
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
    val messageState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val context = LocalContext.current
    val pickPhoto =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            if (uri != null) {
                Log.d("DetailMessageScreen", "Selected image URI: $uri")
            }
        }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(

            title = {
                XText(
                    text = "Dr Ohio",
                    fontSize = XFontSize.Large,
                    fontWeight = FontWeight.Medium
                )
            }, actions = {
                XIcon(
                    icon = painterResource(id = R.drawable.ic_phone_cal),
                    tint = healthTheme,
                    modifier = Modifier.clickable {
                        context.showToast()
                    })
                Spacer(modifier = Modifier.width(XPadding.medium))
                XIcon(
                    icon = painterResource(id = R.drawable.ic_video),
                    tint = healthTheme,
                    modifier = Modifier.clickable {
                        context.showToast()
                    })
            }, navigationIcon = {
                XIcon(icon = Icons.Default.ArrowBack, modifier = Modifier.clickable {
                    setEvent(DetailMessageContract.Event.back)
                })
            })

    },
        bottomBar = {
            Row(
                modifier = Modifier
                    .navigationBarsPadding()
                    .imePadding(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                XIcon(
                    icon = painterResource(id = R.drawable.ic_photo_library),
                    tint = healthTheme,
                    modifier = Modifier.clickable {
                        pickPhoto.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
                    })
                Spacer(modifier = Modifier.width(XPadding.large))
                XIcon(
                    icon = painterResource(id = R.drawable.ic_voice),
                    healthTheme,
                    modifier = Modifier.clickable {
                        context.showToast()
                    })
                XTextField(

                    placeholder = "Enter your Message",
                    textState = messageState.value,

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
            modifier = Modifier
                .padding(it)


        ) {
            items(FakeData.messagesFake.size) {
                val isMess = FakeData.messagesFake[it]
                MessageCard(isMess)
            }
        }
    }
}

@Composable
private fun MessageCard(mess: Message) {
    val alignMessage =
        when (mess.isSentByUser) {
            true -> {
                Arrangement.End
            }

            false -> {
                Arrangement.Start
            }
        }
    Row(
        horizontalArrangement = alignMessage,
        modifier = Modifier
            .fillMaxWidth()
            .padding(XPadding.extraSmall),
        verticalAlignment = Alignment.CenterVertically

    ) {
        if (!mess.isSentByUser) {
            Image(
                painter = painterResource(id = R.drawable.ic_health),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(20.dp)

            )
        }
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
                    .background(if (mess.isSentByUser) Color.Cyan else Color.LightGray)
                    .padding(XPadding.medium)

            ) {
                XText(text = mess.content)
            }
        }
    }
}

fun Context.showToast() {
    Toast.makeText(this, "Feature Coming Soon", Toast.LENGTH_LONG).show()
}