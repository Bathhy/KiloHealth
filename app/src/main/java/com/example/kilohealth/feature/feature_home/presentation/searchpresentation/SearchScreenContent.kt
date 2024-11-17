package com.example.kilohealth.feature.feature_home.presentation.searchpresentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XIcon
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XText
import com.example.kilohealth.x_component.XTextField

@Composable
fun SearchBar(
    uiState:SearchContract.State,
    setEvent:(SearchContract.Event)-> Unit
){
    XTextField(
        textState = uiState.query,
        placeholder = "Search Health Blog",
        keyboardActions = KeyboardActions(
            onDone = {
                setEvent(SearchContract.Event.SearchBlog)
            }
        ),
        onTextChange = {
            setEvent(SearchContract.Event.QuerySearch(query = it))
        },
        trailingIcon = {
            XIcon(
                icon = Icons.Default.Clear,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        setEvent(SearchContract.Event.ClearQuery)
                    }
            )
        }
    )
}

@Composable
fun SearchNotFound(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        XIcon(
            icon = Icons.Default.Search,
            modifier = Modifier
                .size(XPadding.large * 10),
            tint = healthTheme
        )
        XText(
            "Result Not Found",
            color = Color.LightGray
        )
    }
}