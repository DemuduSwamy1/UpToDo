package com.example.uptodo.bottomNavigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mybottomnavigation.BottomNavItem
import com.example.uptodo.IndexScreen
import com.example.uptodo.navigation.Screen
import com.example.uptodo.onBoardingScreens.SettingsScreen
import com.example.uptodo.screens.CalendarScreen
import com.example.uptodo.screens.ProfileScreen
import com.example.uptodo.TasksDataViewModel

@RequiresApi(Build.VERSION_CODES.O)
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
            CalendarScreen()
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