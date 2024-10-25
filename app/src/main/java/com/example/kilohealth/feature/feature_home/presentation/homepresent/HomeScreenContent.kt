package com.example.kilohealth.feature.feature_home.presentation.homepresent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import coil3.compose.AsyncImage
import com.example.kilohealth.R
import com.example.kilohealth.data.FakeData
import com.example.kilohealth.data.TabBarCategory
import com.example.kilohealth.feature.feature_home.domain.model.BlogListModel
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XIcon
import com.example.kilohealth.x_component.XImageNetwork
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XText
import kotlin.math.abs


@Composable
 fun GridCardHomeScreen(setEvent: () -> Unit, grid: BlogListModel){
    Box(
        modifier = Modifier

            .padding(
                horizontal = XPadding.extraSmall,
                vertical = XPadding.extraSmall
            )
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                setEvent()
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
                    error = R.drawable.health
                )

            }
            XText(text = grid.name, color = Color.Black)
            XText(
                text = grid.description,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black
            )
        }
    }
}

@Composable
 fun HomeTabBar() {
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
 fun TopHomeScreen(pagerState: PagerState, uiState: HomeContract.State) {
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
                        fraction = (1f - abs(dotOffset)).coerceIn(0f, 2f)
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
 fun SearchBar() {
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