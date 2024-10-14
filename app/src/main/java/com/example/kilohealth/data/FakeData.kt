package com.example.kilohealth.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.kilohealth.R
import com.example.kilohealth.navigation.Screen

data class PagerData(
    val image: Int
)

data class BottomNavData(
    val icon: Int,
    val route: String
)

data class TabBarCategory(
    val label: String
)

internal object FakeData {
    val bottomNavFakeData = listOf(
        BottomNavData(icon = R.drawable.ic_home, route = Screen.HOme.route),
        BottomNavData(icon = R.drawable.ic_message, route = Screen.Message.route),
        BottomNavData(icon = R.drawable.ic_notifi, route = Screen.Notification.route),
        BottomNavData(icon = R.drawable.ic_person, route = Screen.Profile.route)

    )
    val pagerFakeData = listOf(
        PagerData(R.drawable.deep),
        PagerData(R.drawable.ic_health),
        PagerData(R.drawable.deep), PagerData(R.drawable.ic_health)
    )

    val cateFakeListData :SnapshotStateList<TabBarCategory> = mutableStateListOf(
        TabBarCategory("All"),
        TabBarCategory("Kidney"),
        TabBarCategory("Lung"),
        TabBarCategory("Drug")
    )
}