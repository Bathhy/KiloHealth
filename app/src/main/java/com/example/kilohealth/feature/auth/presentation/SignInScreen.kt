package com.example.kilohealth.feature.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.kilohealth.navigation.Screen
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XFontSize
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XText
import com.example.kilohealth.x_component.XTextField

fun NavGraphBuilder.toSignIn() = composable(
    route = Screen.SignIn.route,
    content = {
        SignInScreen()
    }
)


@Composable
fun SignInScreen() {
    val textFieldState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    Scaffold {
        Column(
            modifier = Modifier
                .padding(horizontal = XPadding.extraLarge)
                .padding(it)

                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            XText(text = "Login ", color = healthTheme, fontSize = XFontSize.ExtraLarge)
            Spacer(modifier = Modifier.height(XPadding.extraLarge))
            XTextField(textState = textFieldState.value, placeholder = "Email")
            Spacer(modifier = Modifier.height(XPadding.extraLarge))
            XTextField(textState = textFieldState.value, placeholder = "Password")
            Spacer(modifier = Modifier.height(XPadding.extraLarge))
            XYRectangleButton(
                fontSize = XFontSize.Large,
                labelButton = "Sign In", modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(healthTheme)

                    .padding(vertical = XPadding.extraSmall, horizontal = XPadding.extraLarge * 3)
            )
            Spacer(modifier = Modifier.height(XPadding.extraLarge))
            XYRectangleButton(
                fontSize = XFontSize.Large,
                labelButton = "Continue with Google ", modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(healthTheme)

                    .padding(vertical = XPadding.extraSmall, horizontal = XPadding.extraLarge * 3)
            )
        }
    }
}

@Composable
internal fun XYRectangleButton(
    labelButton: String,
    onClick: () -> Unit = {},
    modifier: Modifier,
    fontSize: TextUnit = XFontSize.Medium
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            XText(
                text = labelButton,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = XPadding.extraLarge),
                fontSize = fontSize
            )

        }
    }
}