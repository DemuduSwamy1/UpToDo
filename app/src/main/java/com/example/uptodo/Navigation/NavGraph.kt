package com.example.uptodo.Navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uptodo.DashBoardScreen
import com.example.uptodo.OnBoardingScreens.FirstIntroScreen
import com.example.uptodo.OnBoardingScreens.LoginScreen
import com.example.uptodo.OnBoardingScreens.OnBoardingScreen
import com.example.uptodo.OnBoardingScreens.SecondIntroScreen
import com.example.uptodo.OnBoardingScreens.SignUpScreen
import com.example.uptodo.OnBoardingScreens.ThirdIntroScreen
import com.example.uptodo.OnBoardingScreens.WelcomeScreen
import com.example.uptodo.Screens.ProfileScreen
import com.example.uptodo.TasksDataViewModel

@Composable
fun SetUpNavGraph(navController: NavHostController, tasksDataViewModel: TasksDataViewModel) {
    NavHost(navController = navController, startDestination = Screen.OnoardingScreen.route) {
        composable(route = Screen.OnoardingScreen.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(route = Screen.FirstIntroScreen.route) {
            FirstIntroScreen(navController = navController)
        }
        composable(route = Screen.SecondIntroScreen.route) {
            SecondIntroScreen(navController = navController)
        }
        composable(route = Screen.ThirdIntroScreen.route) {
            ThirdIntroScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = Screen.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.DashBoardScreen.route) {
            DashBoardScreen(tasksDataViewModel = tasksDataViewModel)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController, tasksDataViewModel = tasksDataViewModel)
        }
    }
}