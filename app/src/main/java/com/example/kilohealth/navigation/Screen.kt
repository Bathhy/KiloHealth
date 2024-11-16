package com.example.kilohealth.navigation

enum class EnumScreen {
    LOGIN,
    SIGNUP,
    SPLASH,
    HOME,
    NOTIFICATION,
    MESSAGE,
    DETAIL_MESSAGE,
    PROFILE,
    DETAIL,
    FAV,
    NAVIGATION,SEARCH
}
sealed class Screen(val route:String){
    data object SignIn :Screen(EnumScreen.LOGIN.name)
    data object SignUp :Screen(EnumScreen.SIGNUP.name)
    data object Splash : Screen(EnumScreen.SPLASH.name)
    data object DetailMessage : Screen(EnumScreen.DETAIL_MESSAGE.name)
    data object HOme: Screen(EnumScreen.HOME.name)
    data object Notification : Screen(EnumScreen.NOTIFICATION.name)
    data object Message: Screen(EnumScreen.MESSAGE.name)
    data object Profile: Screen(EnumScreen.PROFILE.name)
    data object Favorite: Screen(EnumScreen.FAV.name)
    data object Search:Screen(EnumScreen.SEARCH.name)
    data object DashBoard: Screen(EnumScreen.NAVIGATION.name)
    data class Detail(val id : Int): Screen(EnumScreen.DETAIL.name+"/{id}"){
        fun passId(id:Int):String{
            return EnumScreen.DETAIL.name +"/$id";
        }
    }
}
object Route{
    const val ROOT = "root_graph"
    const val SPLASH = "splash_graph"
    const val HEALTH = "health_graph"
}