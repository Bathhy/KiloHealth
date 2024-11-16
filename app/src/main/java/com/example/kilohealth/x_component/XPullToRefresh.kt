package com.example.kilohealth.x_component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kilohealth.feature.feature_home.presentation.homepresent.HomeContract
import com.example.kilohealth.ui.theme.healthTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XPullToRefresh(
    swipeRefresh: PullToRefreshState,
    setUpEvent: ()-> Unit,
    modifier: Modifier= Modifier,
    isRefreshState : Boolean
){
    PullToRefreshContainer(
        state = swipeRefresh,
        modifier = modifier,
        contentColor = healthTheme,

        )
    if (swipeRefresh.isRefreshing) {
        LaunchedEffect(true) {
          setUpEvent()
        }
    }

    LaunchedEffect(isRefreshState) {
        if (isRefreshState) {
            swipeRefresh.startRefresh()
        } else {
            swipeRefresh.endRefresh()
        }
    }

}