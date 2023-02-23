package com.example.uptodo.navigation


import DataStoreManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uptodo.DashBoardScreen
import com.example.uptodo.onBoardingScreens.FirstIntroScreen
import com.example.uptodo.onBoardingScreens.LoginScreen
import com.example.uptodo.onBoardingScreens.OnBoardingScreen
import com.example.uptodo.onBoardingScreens.SecondIntroScreen
import com.example.uptodo.onBoardingScreens.SignUpScreen
import com.example.uptodo.onBoardingScreens.ThirdIntroScreen
import com.example.uptodo.onBoardingScreens.WelcomeScreen
import com.example.uptodo.screens.ProfileScreen
import com.example.uptodo.TasksDataViewModel
import kotlinx.coroutines.CoroutineScope

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    tasksDataViewModel: TasksDataViewModel,
    context: Context,
    dataStoreManager: DataStoreManager,
    scope: CoroutineScope
) {
    NavHost(navController = navController, startDestination = Screen.OnBoardingScreen.route) {
        composable(route = Screen.OnBoardingScreen.route) {
            OnBoardingScreen(navController = navController, dataStoreManager = dataStoreManager)
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
            LoginScreen(navController = navController, context = context, scope = scope)
        }
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController, context = context, scope = scope)
        }
        composable(route = Screen.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.DashBoardScreen.route) {
            DashBoardScreen(tasksDataViewModel = tasksDataViewModel, context = context)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController, tasksDataViewModel = tasksDataViewModel)
        }
    }
}