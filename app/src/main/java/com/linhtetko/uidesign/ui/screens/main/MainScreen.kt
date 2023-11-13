package com.linhtetko.uidesign.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.linhtetko.uidesign.ui.screens.Screen
import com.linhtetko.uidesign.ui.screens.details.DetailScreen
import com.linhtetko.uidesign.ui.screens.home.HomeScreen
import com.linhtetko.uidesign.ui.theme.UIDesignTheme
import com.linhtetko.uidesign.ui.vos.BottomNavItemVO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var bottomNavItems by remember {
        mutableStateOf(BottomNavItemVO.bottomNavItems)
    }
    val navController = rememberNavController()

    var showDetail by remember {
        mutableStateOf(false)
    }


    Scaffold(modifier = modifier, bottomBar = {
        NavigationBar(containerColor = MaterialTheme.colorScheme.surface, tonalElevation = 0.dp) {
            bottomNavItems.forEach { item ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = Color.Black,
                        indicatorColor = MaterialTheme.colorScheme.surface,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface
                    ), selected = item.isSelected, onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }

                        val temp = bottomNavItems.toList()
                        bottomNavItems = temp.map { it.copy(isSelected = item.id == it.id) }
                    }, icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(item.icon),
                            contentDescription = stringResource(id = item.label)
                        )
                    }, label = { Text(text = stringResource(id = item.label)) })
            }
        }
    }) { innerPadding ->
        NavHost(navController = navController, startDestination = Screen.ROUTE_HOME) {
            composable(Screen.Home.route) {
                HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    onTapShow = { showDetail = !showDetail })
            }
            composable(Screen.Wallet.route) {
//                HomeScreen(modifier = Modifier.padding(innerPadding))
            }
            composable(Screen.More.route) {
//                HomeScreen(modifier = Modifier.padding(innerPadding))

            }
        }
    }
    if (showDetail)
        DetailScreen(onTapBack = {
            showDetail = false
            navController.popBackStack()
        })
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    UIDesignTheme {
        MainScreen()
    }
}