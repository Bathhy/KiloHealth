package com.example.kilohealth.feature.feature_home.presentation.searchpresentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.kilohealth.R
import com.example.kilohealth.feature.notification.presentation.NotificationContract
import com.example.kilohealth.navigation.Screen
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XFontSize
import com.example.kilohealth.x_component.XIcon
import com.example.kilohealth.x_component.XImageNetwork
import com.example.kilohealth.x_component.XLazyColumn
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XText
import com.example.kilohealth.x_component.XTextField
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.toSearchRoute(
    setEffect: (SearchContract.Effect) -> Unit
) = composable(
    route = Screen.Search.route,
    content = {

        val vm: SearchVM = koinViewModel()
        val uiState = vm.state.collectAsState().value
        LaunchedEffect(Unit) {
            vm.effect.onEach {
                when (it) {
                    SearchContract.Effect.Nav.Back -> {
                        setEffect(SearchContract.Effect.Nav.Back)
                    }
                }
            }.collect()
        }
        SearchScreen(setEvent = vm::onEvent, uiState)
    }
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    setEvent: (SearchContract.Event) -> Unit,
    uiState: SearchContract.State
) {
    Scaffold(
        topBar = {
            TopAppBar(

                title = {
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
                },
                colors = TopAppBarColors(
                    containerColor = healthTheme,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    scrolledContainerColor = Color.Transparent,
                    actionIconContentColor = Color.White
                ),
                navigationIcon = {
                    XIcon(
                        icon = R.drawable.ic_back,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                setEvent(SearchContract.Event.Back)
                            }, tint = Color.White
                    )
                }
            )
        }
    ) { padding ->

        when (uiState.searchNotFound) {
            true -> {
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

            false -> {
                XLazyColumn(
                    modifier = Modifier.padding(padding)
                ) {
                    items(uiState.searchList.size) { searchList ->
                        val searchData = uiState.searchList[searchList]
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = XPadding.large, vertical = XPadding.medium),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                XText(
                                    searchData.name,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = XFontSize.ExtraLarge,
                                )
                                Spacer(modifier = Modifier.height(XPadding.large))
                                XText(
                                    searchData.description,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = XFontSize.Medium,
                                    color = Color.LightGray,
                                    overflow = TextOverflow.Clip,
                                    maxLines = 2
                                )
                            }
                            XImageNetwork(
                                url = searchData.thumbnail,
                                modifier = Modifier
                                    .size(XPadding.extraLarge * 6)
                                    .clip(shape = RoundedCornerShape(15)),
                                contentScale = ContentScale.Crop,
                                error = R.drawable.health


                            )
                        }
                    }
                }
            }
        }
    }
}