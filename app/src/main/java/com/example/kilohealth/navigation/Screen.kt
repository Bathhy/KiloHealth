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
    data object DetailMessage : Screen(EnumScreen.DETAIL_MESSAGE.name)
    data object HOme: Screen(EnumScreen.HOME.name)
    data object Notification : Screen(EnumScreen.NOTIFICATION.name)
    data object Message: Screen(EnumScreen.MESSAGE.name)
    data object Profile: Screen(EnumScreen.PROFILE.name)
    data class Detail(val id : Int): Screen(EnumScreen.DETAIL.name+"/{id}"){
        fun passId(id:Int):String{
            return EnumScreen.DETAIL.name +"/$id";
        }
    }
}
object Route{
    const val ROOT = "root_graph"
    const val NOTE = "note_graph"
    const val AUTH = "auth_graph"
}