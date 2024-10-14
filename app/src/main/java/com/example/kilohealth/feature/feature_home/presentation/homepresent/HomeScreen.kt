package com.example.kilohealth.feature.feature_home.presentation.homepresent

import android.util.Log
import android.util.Patterns
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XIcon
import com.example.kilohealth.x_component.XImageNetwork
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    setEvent: (HomeContract.Event) -> Unit,
    uiState: HomeContract.State
) {
    val pagerState = rememberPagerState {
        FakeData.pagerFakeData.size
    }
//    val selectedTabIndex = remember {FakeData.cateFakeListData }
    val searchTextFieldState = remember { TextFieldValue("") }
    var draggingItemIndex by remember {
        mutableStateOf<Int?>(null)
    }
    var offsetX by remember {
        mutableStateOf(0f)
    }
    Scaffold(
        topBar = {
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
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(horizontal = XPadding.extraLarge)
                .padding(bottom = XPadding.extraLarge * 5)
        ) {
            item {
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
                Spacer(modifier = Modifier.height(XPadding.large))
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                ) {
                    HorizontalPager(state = pagerState) {
                        val data = FakeData.pagerFakeData[it]
                        Image(
                            painter = painterResource(id = data.image),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        repeat(FakeData.pagerFakeData.size) { index ->
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(if (index == pagerState.currentPage) healthTheme else Color.LightGray)
                                    .size(8.dp)
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(XPadding.large))
                XText(text = "All Categories")
                Spacer(modifier = Modifier.height(XPadding.large))
                LazyRow {
                    items(FakeData.cateFakeListData) { indx ->
                        val fakeCate = FakeData.cateFakeListData.indexOf(indx)
                        val valCate = FakeData.cateFakeListData[fakeCate]
                        Box(
                            modifier = Modifier
                                .padding(horizontal = XPadding.medium)
//                                .clip(shape = RoundedCornerShape(10.dp))
//                                .offset {
//                                    IntOffset(
//                                        if (draggingItemIndex == fakeCate) offsetX.roundToInt() else 0,
//                                        0
//                                    )
//                                }
//                                .pointerInput(Unit) {
//                                    detectDragGestures(
//                                        onDragStart = {
//                                            draggingItemIndex = fakeCate
//                                            offsetX = 0f
//                                        },
//                                        onDrag = { change, dragAmount ->
//                                            offsetX += dragAmount.x
//                                            change.consume()
//                                            val newIndx= ((fakeCate * 100) + offsetX / 10)
//                                                .toInt()
//                                                .coerceIn(0, selectedTabIndex.size - 1)
//                                            if(newIndx != fakeCate){
//                                                selectedTabIndex.removeAt(fakeCate)
//                                                selectedTabIndex.add(newIndx, indx)
//                                                draggingItemIndex = newIndx
//                                            }
//                                        },
//                                        onDragEnd = {
//                                            draggingItemIndex = null
//                                            offsetX = 0f
//                                        }
//                                    )
//                                }
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(vertical = XPadding.medium, horizontal = XPadding.large)

                        ) {
                            Column {
                                Box(
                                    modifier = Modifier.size(120.dp)
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
            }
            item {
                Spacer(modifier = Modifier.height(XPadding.large))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(700.dp)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        userScrollEnabled = false,
                    ) {
                        items(uiState.homeBlogState.size) {
                            val grid = uiState.homeBlogState[it]

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
                                    .shadow(1.dp, shape = RoundedCornerShape(XPadding.medium))
                                    .clip(shape = RoundedCornerShape(XPadding.medium))
                                    .background(color = Color.White)
                            ) {
                                Column(
                                ) {
                                    Box(
                                    ) {
//                                        XImageNetwork(url ="http://13.212.176.85:5100/storage/v1-4.jpg")
////                                        AsyncImage(
////                                            model = "http://13.212.176.85:5100/storage/v1-4.jpg",
////                                            contentDescription =null,
////                                            modifier = Modifier
////                                                .height(75.dp)
////                                                .fillMaxWidth()
////                                        )

                                        Image(
                                            painter = rememberAsyncImagePainter(
                                                model = ImageRequest.Builder(LocalContext.current)
                                                    .data("http://13.212.176.85:5100/storage/v1-4.jpg")
                                                    .size(Size.ORIGINAL)
                                                    .build()
                                            ),
                                            contentDescription = null,
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier
                                                .height(75.dp)
                                                .fillMaxWidth()
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
