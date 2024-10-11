package com.example.kilohealth.navigation

enum class EnumScreen {
    LOGIN,
    SIGNUP,
    HOME,
    NOTIFICATION,
    MESSAGE,
    DETAIL_MESSAGE,
    PROFILE,
    DETAIL
}
sealed class Screen(val route:String){
    object DetailMessage : Screen(EnumScreen.DETAIL_MESSAGE.name)
    object HOme: Screen(EnumScreen.HOME.name)
    object Notification : Screen(EnumScreen.NOTIFICATION.name)
    object Message: Screen(EnumScreen.MESSAGE.name)
    object Profile: Screen(EnumScreen.PROFILE.name)
    object Detail: Screen(EnumScreen.DETAIL.name)
}
object Route{
    const val ROOT = "root_graph"
    const val NOTE = "note_graph"
    const val AUTH = "auth_graph"
}