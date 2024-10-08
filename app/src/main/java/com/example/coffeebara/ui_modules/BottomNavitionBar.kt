package com.example.coffeebara.ui_modules

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.coffeebara.navigation.Routes
import com.example.coffeebara.ui.theme.BottomBar
import com.example.coffeebara.ui.theme.DeepDarkBrown

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = BottomBar,
        modifier = Modifier.height(70.dp)
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        Row {
            NavBarItems.BarItems.forEachIndexed { index, navItem ->
                NavigationBarItem(
                    selected = currentRoute == navItem.route,
                    onClick = {
                        navController.navigate(navItem.route) {
                            popUpTo(Routes.Map.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (currentRoute == navItem.route) navItem.onSelectedIcon else navItem.selectIcon,
                            contentDescription = navItem.title,
                            modifier = Modifier.size(30.dp),
                            tint = if (currentRoute == navItem.route) Color.Black else Color.White
                        )
                    },
                    label = {
                        Text(
                            text = navItem.title,
                            color = Color.White
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        unselectedIconColor = Color.White,
                        indicatorColor = Color.White // 선택된 항목의 배경 색상
                    ),

                    )
                if (index < NavBarItems.BarItems.size - 1) {
                    Divider(
                        color = Color.White,
                        modifier = Modifier
                            .height(80.dp) // 네비게이션 아이템의 높이와 맞춤
                            .width(1.dp)
                    )
                }
            }
        }
    }
}

data class BarItem(
    val title: String,
    val selectIcon: ImageVector,
    val onSelectedIcon: ImageVector,
    val route: String,
)

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "홈",
            selectIcon = Icons.Default.Home,
            onSelectedIcon = Icons.Outlined.Home,
            route = Routes.Home.route
        ),
        BarItem(
            title = "지도",
            selectIcon = Icons.Default.LocationOn,
            onSelectedIcon = Icons.Outlined.LocationOn,
            route = Routes.Map.route + "?deviceid=0"
        ),
        BarItem(
            title = "마이 페이지",
            selectIcon = Icons.Default.Person,
            onSelectedIcon = Icons.Outlined.Person,
            route = Routes.MyPage.route
        )
    )
}