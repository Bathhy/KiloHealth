package com.example.kilohealth.x_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun XPageCardShimmer() {
    Box(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(10.dp)
            )
            .background(Color.LightGray)
            .height(150.dp)
            .fillMaxWidth()

    ) {

    }

}


@Composable
@Preview
fun XCardShimmer() {
    Box(
        modifier = Modifier

            .padding(
                horizontal = XPadding.extraSmall,
                vertical = XPadding.extraSmall
            )
            .fillMaxWidth()
            .height(200.dp)
            .shadow(3.dp, shape = RoundedCornerShape(XPadding.medium))
            .clip(shape = RoundedCornerShape(XPadding.medium))
            .background(color = Color.White)
    ) {
        Column {
            Box(
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .height(80.dp)
                        .fillMaxWidth(),
                )
            }
            Spacer(modifier = Modifier.height(XPadding.medium))
            Box(
                modifier = Modifier
                    .padding(horizontal = XPadding.large)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(5.dp))
                    .padding(vertical = XPadding.extraLarge),

                )
            Spacer(modifier = Modifier.height(XPadding.medium))
            Box(
                modifier = Modifier
                    .padding(horizontal = XPadding.large)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(5.dp))
                    .padding(vertical = XPadding.extraLarge),

                )
        }
    }
}