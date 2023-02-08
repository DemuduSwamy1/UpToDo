package com.example.uptodo.BottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mybottomnavigation.BottomNavItem
import com.example.uptodo.IndexScreen
import com.example.uptodo.Navigation.Screen
import com.example.uptodo.OnBoardingScreens.SettingsScreen
import com.example.uptodo.Screens.ProfileScreen
import com.example.uptodo.TasksDataViewModel

@Composable
fun BottomNavigationGraph(
    navBarController: NavHostController,
    tasksDataViewModel: TasksDataViewModel,

    ) {
    NavHost(navController = navBarController, startDestination = BottomNavItem.Index.screen_route) {
        composable(BottomNavItem.Index.screen_route) {
            IndexScreen(navController = navBarController, tasksDataViewModel = tasksDataViewModel)
        }
        composable(BottomNavItem.Calendar.screen_route) {
            NetworkScreen()
        }
        composable(BottomNavItem.AddTask.screen_route) {
            NotificationScreen()
        }
        composable(BottomNavItem.Focus.screen_route) {
            AddPostScreen()
        }
        composable(BottomNavItem.Profile.screen_route) {
            ProfileScreen(navController = navBarController, tasksDataViewModel = tasksDataViewModel)
        }
        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen(navController = navBarController)
        }
        composable(route = Screen.IndexScreen.route) {
            IndexScreen(navController = navBarController, tasksDataViewModel = tasksDataViewModel)
        }
    }
}