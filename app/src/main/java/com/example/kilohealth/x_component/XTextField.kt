package com.example.kilohealth.x_component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kilohealth.ui.theme.healthTheme

@Composable
fun XTextField(
    textstate: TextFieldValue,
    placeholder: String = "",
    onTextChange: (TextFieldValue) -> Unit = {},
    height: Dp = 50.dp,
    borderRounded: Dp = 5.dp,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefixicon : @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    borderColor: Color = Color.Black.copy(alpha = 0.5f),
    backgroundColor: Color = Color.White.copy(0.5f),
    obsecuretext: Boolean = false,
    validateinput: ((String)-> String?)? = null,
    textStyle: TextStyle = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black
    ),
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
    ),
    errorMessage: String? = null,
    keyboardActions: KeyboardActions = KeyboardActions(onDone = {}),
) {
    var currentErrorMessage by remember { mutableStateOf<String?>(errorMessage) }
    Row(
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(borderRounded)),

        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(

            visualTransformation = visualTransformation,
            placeholder = {
                if (textstate.text.isEmpty()) {
                    Text(text = placeholder, style = textStyle)
                }
            },
            leadingIcon = prefixicon,
            trailingIcon = {
                trailingIcon?.invoke()
            },
            value = textstate,
            onValueChange = {
                onTextChange(it)
                currentErrorMessage= validateinput?.invoke(it.text)
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            textStyle = textStyle,
            maxLines = 1,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            isError = currentErrorMessage != null,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = healthTheme,
                unfocusedTextColor = Color.Transparent
            )
        )

    }
    currentErrorMessage.let {
        it?.let { it1 -> XText(text = it1, color = Color.Red, ) }
    }
}
