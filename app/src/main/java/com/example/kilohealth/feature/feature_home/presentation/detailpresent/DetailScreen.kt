package com.example.kilohealth.feature.feature_home.presentation.detailpresent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kilohealth.R
import com.example.kilohealth.data.FakeData
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XFontSize
import com.example.kilohealth.x_component.XIcon
import com.example.kilohealth.x_component.XImageNetwork
import com.example.kilohealth.x_component.XLazyColumn
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XPullToRefresh
import com.example.kilohealth.x_component.XText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    setEvent: (DetailContract.Event) -> Unit,
    uiState: DetailContract.State
) {
    val pullRefreshState  = rememberPullToRefreshState()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = healthTheme,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    scrolledContainerColor = Color.Transparent,
                    actionIconContentColor = Color.White
                ),
                title = { XText(text = uiState.uiState.name) },
                navigationIcon = {
                    XIcon(
                        icon =  R.drawable.ic_back,
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            setEvent(DetailContract.Event.Back)
                        })
                },
                actions = {
                    XIcon(
                        icon = Icons.Default.Favorite,
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            setEvent(DetailContract.Event.Fav)
                        })
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.nestedScroll(pullRefreshState.nestedScrollConnection)
        ){
            XLazyColumn(
                modifier = Modifier.padding(it)
            ) {
                item {
                    Spacer(modifier = Modifier.height(XPadding.medium))
                    XImageNetwork(
                        url = uiState.uiState.thumbnail,
                        error = R.drawable.health,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.height(XPadding.medium))
                    XText(
                        text = uiState.uiState.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = XFontSize.Large
                    )
                    XText(text = uiState.uiState.content, fontSize = XFontSize.Medium)
                    Spacer(modifier = Modifier.height(XPadding.extraSmall))

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        items(FakeData.detailMediaIcon.size) {
//                       XIcon(icon = FakeData.detailMediaIcon[it].icon)
                            XIcon(icon = FakeData.detailMediaIcon[it].icon , tint = Color.Unspecified)
                        }
                    }
                }
            }
            XPullToRefresh(
                swipeRefresh = pullRefreshState,
                setUpEvent = {
                    setEvent(DetailContract.Event.RefreshDetailScreen)
                },
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshState = uiState.isRefresh
            )
        }
    }
}