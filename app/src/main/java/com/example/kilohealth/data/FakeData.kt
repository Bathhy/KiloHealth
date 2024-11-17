package com.example.kilohealth.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.kilohealth.R
import com.example.kilohealth.navigation.Screen

//data class Message(
//    val author:String,
//    val text:String
//){
//    val isFromMe: Boolean
//        get() { author}
//}
data class Message(val content: String, val isSentByUser: Boolean)
data class PagerData(
    val image: List<String>
)

data class NotificationData(
    val label: String,
    val dateTime: String

)

data class BottomNavData(
    val icon: Int,
    val route: String
)


data class TabBarCategory(
    val label: String
)

data class UserHealthStatus(
    val label: String,
    val healthNumStat: String,
    val icon: Int
)

data class DetailIcon(
    val icon: Any
)

internal object FakeData {

    val notificationFakeData = listOf(
        NotificationData(
            label = "Kon nek Cheu hz ",
            dateTime = "Aug 12, 2020 at 12:08 PM"
        ),
        NotificationData(
            label = "Kon nek Cheu hz ",
            dateTime = "Aug 12, 2020 at 12:08 PM"
        ),
        NotificationData(
            label = "Kon nek Cheu hz ",
            dateTime = "Aug 12, 2020 at 12:08 PM"
        ),
        NotificationData(
            label = "Kon nek Cheu hz ",
            dateTime = "Aug 12, 2020 at 12:08 PM"
        ),
        NotificationData(
            label = "Kon nek Cheu hz ",
            dateTime = "Aug 12, 2020 at 12:08 PM"
        ),
        NotificationData(
            label = "Kon nek Cheu hz ",
            dateTime = "Aug 12, 2020 at 12:08 PM"
        ),
        NotificationData(
            label = "Kon nek Cheu hz ",
            dateTime = "Aug 12, 2020 at 12:08 PM"
        ),
    )
    val detailMediaIcon = listOf(
        DetailIcon(icon = Icons.Default.Share),
        DetailIcon(icon = R.drawable.ic_fb),
        DetailIcon(icon = R.drawable.ic_telegram)
    )
    val messagesFake = listOf(
        Message(content = "Hello! How are you?", isSentByUser = false),
        Message(content = "I'm good, thanks! How about you?", isSentByUser = true),
        Message(content = "What are you up to today?", isSentByUser = false),
        Message(content = "Just working on a project. You?", isSentByUser = true),
        Message(content = "Same here, trying to finish up some tasks.", isSentByUser = false),
        Message(content = "Let's catch up later!", isSentByUser = true),
        Message(content = "Sounds good! Talk to you later.", isSentByUser = false)
    )
    val bottomNavFakeData = listOf(
        BottomNavData(icon = R.drawable.ic_home, route = Screen.HOme.route),
        BottomNavData(icon = R.drawable.ic_message, route = Screen.Message.route),
        BottomNavData(icon = R.drawable.ic_notifi, route = Screen.Notification.route),
        BottomNavData(icon = R.drawable.ic_person, route = Screen.Profile.route)

    )
    val healthStatList = listOf(
        UserHealthStatus(
            label = "Heart rate",
            healthNumStat = "215bpm",
            icon = R.drawable.ic_heart_rate
        ),
        UserHealthStatus(
            label = "Calories",
            healthNumStat = "756cal",
            icon = R.drawable.ic_calories
        ),
        UserHealthStatus(label = "Weight", healthNumStat = "103lbs", icon = R.drawable.ic_weight)
    )
//    val pagerFakeData = listOf(
//        PagerData(R.drawable.deep),
//        PagerData(R.drawable.ic_health),
//        PagerData(R.drawable.deep), PagerData(R.drawable.ic_health)
//    )

    val cateFakeListData: SnapshotStateList<TabBarCategory> = mutableStateListOf(
        TabBarCategory("All"),
        TabBarCategory("Kidney"),
        TabBarCategory("Lung"),
        TabBarCategory("Drug")
    )
}