package com.capstone.hadirai.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capstone.hadirai.ui.components.BottomBar
import com.capstone.hadirai.ui.navigation.Screen
import com.capstone.hadirai.ui.screen.employee.history.HistoryScreen
import com.capstone.hadirai.ui.screen.employee.home.HomeScreen
import com.capstone.hadirai.ui.screen.employee.maps.MapsScreen
import com.capstone.hadirai.ui.screen.employee.profile.ProfileScreen
import com.capstone.hadirai.ui.theme.HadirAITheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HadirAI(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = { BottomBar(navController) },

        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navigationToMaps = { navController.navigate(Screen.Maps.route)} )
            }
            composable(Screen.History.route){
                HistoryScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
            composable(Screen.Maps.route){
                MapsScreen()
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HadirAITheme {
        HadirAI()
    }
}