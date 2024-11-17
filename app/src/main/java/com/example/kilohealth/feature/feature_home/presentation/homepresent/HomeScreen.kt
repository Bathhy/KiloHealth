package com.example.kilohealth.feature.feature_home.presentation.homepresent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.kilohealth.R
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XIcon
import com.example.kilohealth.x_component.XPadding
import com.example.kilohealth.x_component.XPullToRefresh

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    setEvent: (HomeContract.Event) -> Unit,
    uiState: HomeContract.State
) {
    val pagerState = rememberPagerState {
        uiState.pagerState.image.size
    }
    val scrollState = rememberLazyGridState()
    val swipeRefresh = rememberPullToRefreshState()

    Scaffold(

        topBar = {
            TopAppBar(
                title = { },
                actions = {
                    XIcon(
                        icon = Icons.Default.Favorite,
                        tint = healthTheme,
                        modifier = Modifier.clickable {
                            setEvent(HomeContract.Event.Favourite)
                        })
                },
                navigationIcon = {
                    XIcon(
                        icon = R.drawable.ic_health,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )
//            when (isScroll) {
//                true -> {
//                    TopAppBar(
//                        title = {
//                            Box(
//                                contentAlignment = Alignment.Center,
//                                modifier = Modifier
//
//                                    .padding(
//                                        horizontal = XPadding.extraLarge * 2,
//                                        vertical = XPadding.extraLarge
//                                    )
//                            ) {
//                                SearchBar()
//                            }
//                        },
//                    )
//                }
//
//                false -> {
//                    TopAppBar(
//                        title = { },
//                        actions = {
//                            XIcon(icon = Icons.Default.Notifications, tint = healthTheme)
//                        },
//                        navigationIcon = {
//                            XIcon(
//                                icon = R.drawable.ic_health,
//                                modifier = Modifier.size(30.dp)
//                            )
//                        }
//                    )
//                }
//            }
        }
    ) {

        Box(
            modifier = Modifier.nestedScroll(swipeRefresh.nestedScrollConnection),
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = scrollState,
                modifier = Modifier
                    .padding(it)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
                    .padding(horizontal = XPadding.extraLarge)
//                    .navigationBarsPadding()

            ) {
                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {

                    TopHomeScreen(pagerState = pagerState, uiState, setEvent = {
                        setEvent(HomeContract.Event.Search)
                    })
                }
                item(
                    span = {
                        GridItemSpan(2)
                    }
                ) {

                    HomeTabBar(uiState)
                }
                items(uiState.homeBlogState.size) {
                    val grid = uiState.homeBlogState[it]
                    Spacer(modifier = Modifier.height(XPadding.large))
                    GridCardHomeScreen(
                        setEvent = {
                            setEvent(HomeContract.Event.Detail(grid.id))
                        },
                        grid = grid,
                        onClickFavIcon = {
                            setEvent(HomeContract.Event.ToggleFavourite(it))
                        }
                    )
                }


            }
            XPullToRefresh(
                swipeRefresh = swipeRefresh, setUpEvent = {
                    setEvent(HomeContract.Event.IsRefresh)
                },
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshState = uiState.refreshPage
            )
        }

    }
}




