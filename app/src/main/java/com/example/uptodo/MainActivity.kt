package com.example.uptodo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mybottomnavigation.BottomNavItem
import com.example.uptodo.BottomNavigation.BottomNavigationGraph
import com.example.uptodo.Dialogues.AddTaskDialog

import com.example.uptodo.Navigation.SetUpNavGraph
import com.example.uptodo.ui.theme.UpToDoTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    lateinit var tasksDataViewModel: TasksDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UpToDoTheme {
                SetStatusBarColour(Color(0xFF121212))
                navController = rememberNavController()
                tasksDataViewModel = TasksDataViewModel()
                SetUpNavGraph(
                    navController = navController,
                    tasksDataViewModel = tasksDataViewModel
                )
            }
        }
    }
}

@Composable
fun SetStatusBarColour(bottomNavbarColour: Color) {
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
        // setStatusBarColor() and setNavigationBarColor() also exist
        systemUiController.setSystemBarsColor(Color(0xFF121212))
        systemUiController.setNavigationBarColor(bottomNavbarColour)
        onDispose {}
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashBoardScreen(tasksDataViewModel: TasksDataViewModel) {
    val navBarController = rememberNavController()

    val showAddTaskDialog = remember { mutableStateOf(false) }

    if (showAddTaskDialog.value) {
        AddTaskDialog(tasksDataViewModel = tasksDataViewModel, navController = navBarController) {
            showAddTaskDialog.value = it
        }
    }
    Scaffold(
        bottomBar = { BottomNavigation(navController = navBarController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showAddTaskDialog.value = true
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Image(
                    ImageVector.vectorResource(id = R.drawable.add_icon),
                    contentDescription = "Add task"
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        BottomNavigationGraph(
            navBarController = navBarController,
            tasksDataViewModel = tasksDataViewModel
        )
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Index,
        BottomNavItem.Calendar,
        BottomNavItem.AddTask,
        BottomNavItem.Focus,
        BottomNavItem.Profile
    )
    BottomNavigation(
        backgroundColor = Color(0xFF363636), contentColor = Color.Black,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        //Index tab
        BottomNavigationItem(
            icon = {
                Image(
                    imageVector = ImageVector.vectorResource(id = items[0].icon),
                    contentDescription = items[0].title
                )
            },
            label = {
                Text(
                    text = items[0].title, color = Color(0xdeffffff), fontSize = 11.sp, maxLines = 1
                )
            },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.Black.copy(0.4f),
            alwaysShowLabel = true,
            selected = currentRoute == items[0].screen_route,

            onClick = {
                navController.navigate(items[0].screen_route) {

                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }

            }
        )
        // Calendar tab
        BottomNavigationItem(
            icon = {
                Image(
                    imageVector = ImageVector.vectorResource(id = items[1].icon),
                    contentDescription = items[1].title
                )
            },
            label = {
                Text(
                    text = items[1].title, color = Color(0xdeffffff), fontSize = 11.sp, maxLines = 1
                )
            },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.Black.copy(0.4f),
            alwaysShowLabel = true,
            selected = currentRoute == items[1].screen_route,

            onClick = {
                navController.navigate(items[1].screen_route) {

                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }

            }
        )
        // AddTask tab
        BottomNavigationItem(
            icon = {
                Image(
                    imageVector = ImageVector.vectorResource(id = items[2].icon),
                    contentDescription = items[2].title
                )
            },
            label = {
                Text(
                    text = items[2].title, color = Color(0xdeffffff), fontSize = 11.sp, maxLines = 1
                )
            },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.Black.copy(0.4f),
            alwaysShowLabel = true,
            selected = currentRoute == items[2].screen_route,

            onClick = {}
        )
        // Focus tab
        BottomNavigationItem(
            icon = {
                Image(
                    imageVector = ImageVector.vectorResource(id = items[3].icon),
                    contentDescription = items[3].title
                )
            },
            label = {
                Text(
                    text = items[3].title, color = Color(0xdeffffff), fontSize = 11.sp, maxLines = 1
                )
            },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.Black.copy(0.4f),
            alwaysShowLabel = true,
            selected = currentRoute == items[3].screen_route,

            onClick = {
                navController.navigate(items[3].screen_route) {

                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }

            }
        )
        // Profile tab
        BottomNavigationItem(
            icon = {
                Image(
                    imageVector = ImageVector.vectorResource(id = items[4].icon),
                    contentDescription = items[4].title
                )
            },
            label = {
                Text(
                    text = items[4].title, color = Color(0xdeffffff), fontSize = 11.sp, maxLines = 1
                )
            },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.Black.copy(0.4f),
            alwaysShowLabel = true,
            selected = currentRoute == items[4].screen_route,

            onClick = {
                navController.navigate(items[4].screen_route) {

                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }

            })

    }
}
