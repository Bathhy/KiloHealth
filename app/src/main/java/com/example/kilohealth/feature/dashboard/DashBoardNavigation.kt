package com.example.kilohealth.feature.dashboard

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kilohealth.data.FakeData
import com.example.kilohealth.navigation.HealthGraph
import com.example.kilohealth.navigation.Route
import com.example.kilohealth.ui.theme.healthTheme
import com.example.kilohealth.x_component.XIcon
import kotlinx.coroutines.launch

fun NavGraphBuilder.toDashBoardScreen()=composable(
    route = Route.HEALTH,
    content = {
        DashBoardScreen()
    }
)


@Composable
internal fun DashBoardScreen() {
    val scope = rememberCoroutineScope()
    val controller = rememberNavController()
    val navBackStackEntry = controller.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination?.route

    Scaffold(
        bottomBar = {
            if (FakeData.bottomNavFakeData.any {
                    it.route == currentDestination
                }) {
                BottomAppBar(

                    modifier = Modifier.height(100.dp)
                ) {
                    NavigationBar(
                    ) {
                        FakeData.bottomNavFakeData.forEach { bot ->
                            NavigationBarItem(
                                modifier = Modifier.height(90.dp),
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = healthTheme,
                                    indicatorColor = Color.Transparent
                                ),
                                selected = currentDestination == bot.route,
                                onClick = {
                                    scope.launch {
                                        controller.navigate(bot.route) {
                                            launchSingleTop = true
                                        }

                                    }
                                }, icon = {
                                    XIcon(icon = painterResource(id = bot.icon))
                                })
                        }
                    }
                }
            }
        }
    ) {
        padding->
        HealthGraph(controller, modifier = Modifier.padding(padding))
    }
}