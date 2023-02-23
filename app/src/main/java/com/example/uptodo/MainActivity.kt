package com.example.uptodo

import DataStoreManager
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mybottomnavigation.BottomNavItem
import com.example.uptodo.bottomNavigation.BottomNavigationGraph
import com.example.uptodo.dialogues.AddTaskDialog
import com.example.uptodo.navigation.Screen
import com.example.uptodo.navigation.SetUpNavGraph
import com.example.uptodo.ui.theme.UpToDoTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    lateinit var dataStoreManager: DataStoreManager
    lateinit var navController: NavHostController
    lateinit var tasksDataViewModel: TasksDataViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UpToDoTheme {
                SetStatusBarColour(Color(0xFF121212))
                tasksDataViewModel = TasksDataViewModel()
                navController = rememberNavController()
                val scope = rememberCoroutineScope()
                val context = LocalContext.current
                dataStoreManager = DataStoreManager(context)
//                val database = Firebase.database.reference
                SetUpNavGraph(
                    navController = navController,
                    tasksDataViewModel = tasksDataViewModel,
                    context = context,
                    dataStoreManager = dataStoreManager,
                    scope = scope,
                )
                val dataStoreManager = DataStoreManager(context = context)
                val uid = dataStoreManager.uid.collectAsState(initial = "").value
                val email = dataStoreManager.email.collectAsState(initial = "").value
                tasksDataViewModel.userUid.value = uid
                tasksDataViewModel.userEmail.value = email
                if (email.isEmpty()) {
                    Log.d("dataStore_02", email)
                    Log.d("dataStore_03", uid)
                    navController.navigate(Screen.OnBoardingScreen.route)
                } else {
                    Log.d("dataStore_04", email)
                    Log.d("dataStore_05", uid)
                    navController.navigate(Screen.DashBoardScreen.route)
                }

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


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashBoardScreen(tasksDataViewModel: TasksDataViewModel, context: Context) {
    val navBarController = rememberNavController()

    val showAddTaskDialog = remember { mutableStateOf(false) }

    if (showAddTaskDialog.value) {
        AddTaskDialog(
            context = context,
            tasksDataViewModel = tasksDataViewModel,
            navController = navBarController
        ) {
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
