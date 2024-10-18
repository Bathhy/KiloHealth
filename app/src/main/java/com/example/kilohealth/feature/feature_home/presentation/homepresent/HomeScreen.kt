package com.example.kilohealth.feature.feature_home.presentation.homepresent

import android.util.Log
import android.util.Patterns
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import coil3.size.Size
import com.example.kilohealth.R
import com.example.kilohealth.data.FakeData
import com.example.kilohealth.data.TabBarCategory
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XIcon
import com.example.kilohealth.x_component.XImageNetwork
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XText
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    setEvent: (HomeContract.Event) -> Unit,
    uiState: HomeContract.State
) {
    val pagerState = rememberPagerState {
        uiState.pagerState.image.size
    }
//    val selectedTabIndex = remember {FakeData.cateFakeListData }
    val scrollState = rememberLazyGridState()

    val isScroll by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex > 0
        }
    }

    Scaffold(
        topBar = {
            when (isScroll) {
                true -> {
                    TopAppBar(
                        title = {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier

                                    .padding(
                                        horizontal = XPadding.extraLarge * 2,
                                        vertical = XPadding.extraLarge
                                    )
                            ) {
                                SearchBar()
                            }
                        },
                    )
                }

                false -> {
                    TopAppBar(
                        title = { },
                        actions = {
                            XIcon(icon = Icons.Default.Notifications, tint = healthTheme)
                        },
                        navigationIcon = {
                            XIcon(
                                icon = painterResource(id = R.drawable.ic_health),
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    )
                }
            }
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = scrollState,
            modifier = Modifier
                .padding(it)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(horizontal = XPadding.extraLarge)
                .navigationBarsPadding()
        ) {
            item(
                span = {
                    GridItemSpan(2)
                }
            ) {
                TopHomeScreen(pagerState = pagerState , uiState)
            }
            item(
                span = {
                    GridItemSpan(2)
                }
            ) {

                HomeTabBar()
            }
            items(uiState.homeBlogState.size) {
                val grid = uiState.homeBlogState[it]
                Spacer(modifier = Modifier.height(XPadding.large))
                Box(
                    modifier = Modifier

                        .padding(
                            horizontal = XPadding.extraSmall,
                            vertical = XPadding.extraSmall
                        )
                        .fillMaxWidth()
                        .height(200.dp)
                        .clickable {
                            setEvent(HomeContract.Event.detail(grid.id))
                        }
                        .shadow(3.dp, shape = RoundedCornerShape(XPadding.medium))
                        .clip(shape = RoundedCornerShape(XPadding.medium))
                        .background(color = Color.White)
                ) {
                    Column(
                    ) {
                        Box(
                        ) {
                            XImageNetwork(
                                url = grid.thumbnail,
                                modifier = Modifier
                                    .height(80.dp)
                                    .fillMaxWidth(),
                                contentScale = ContentScale.Crop,
                            )

                        }
                        XText(text = grid.name)
                        XText(
                            text = grid.description,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun HomeTabBar() {
    val items = remember { mutableStateListOf(*FakeData.cateFakeListData.toTypedArray()) }
    var draggedItem by remember { mutableStateOf<TabBarCategory?>(null) }
    var dragOffset by remember { mutableStateOf(Offset.Zero) }
    val lazyRowState = rememberLazyListState()
    Column {
        Spacer(modifier = Modifier.height(XPadding.large))
        XText(text = "All Categories")
        Spacer(modifier = Modifier.height(XPadding.large))

        LazyRow {
            items(items) { indx ->
                val fakeCate = FakeData.cateFakeListData.indexOf(indx)
                val valCate = FakeData.cateFakeListData[fakeCate]
                Box(
                    modifier = Modifier
                        .padding(horizontal = XPadding.medium)

                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(
                            vertical = XPadding.medium,
                            horizontal = XPadding.large
                        )
                        .pointerInput(lazyRowState) {
                            detectDragGesturesAfterLongPress { change, dragAmount ->

                                change.consume()
                            }
                        }
                ) {
                    Column {
                        Box(
                            modifier = Modifier.size(100.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.deep),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        XText(text = "test${valCate.label}")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(XPadding.large))
    }
}

@Composable
private fun TopHomeScreen(pagerState: PagerState, uiState: HomeContract.State) {
    Column {
        SearchBar()
        Spacer(modifier = Modifier.height(XPadding.large))
        Box(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
        ) {
            HorizontalPager(state = pagerState) {
                val data = uiState.pagerState.image[it]
                AsyncImage(
                    model =data,
                    contentDescription = null, modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.FillBounds,
                    error = painterResource(id = R.drawable.deep)

                )

            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                repeat(uiState.pagerState.image.size) { index ->
                    val dotOffset =
                        remember(pagerState.currentPage, pagerState.currentPageOffsetFraction) {
                            (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
                        }

                    val dynamicPadding = lerp(
                        start = 2.dp,
                        stop = XPadding.medium,
                        fraction = (1f - abs(dotOffset)).coerceIn(0f, 1f)
                    )
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(if (index == pagerState.currentPage) healthTheme else Color.LightGray)
                            .padding(horizontal = dynamicPadding)
                            .size(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchBar() {
    Box(
        modifier = Modifier
            .shadow(4.dp, shape = RoundedCornerShape(XPadding.medium))
            .clip(shape = RoundedCornerShape(XPadding.medium))
            .background(Color.White)
            .fillMaxWidth()
            .padding(XPadding.large)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = XPadding.medium),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            XText(text = "Search")
            XIcon(icon = Icons.Default.Search, tint = Color.LightGray)
        }
    }
}

@Preview
@Composable
private fun previewHome() {
    HomeScreen(
        setEvent = {

        },
        uiState = HomeContract.State()
    )
}


