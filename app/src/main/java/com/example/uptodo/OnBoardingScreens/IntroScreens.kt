package com.example.uptodo.OnBoardingScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.uptodo.Navigation.Screen
import com.example.uptodo.R

@Composable
fun FirstIntroScreen(
    navController: NavHostController
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color(0xFF121212))
        ) {
            Text(
                text = "SKIP",
                fontSize = 16.sp,
                color = Color(0x70ffffff),
                modifier = Modifier
                    .padding(top = 25.dp, start = 24.dp)
                    .clickable(onClick = { navController.navigate(Screen.DashBoardScreen.route) })
            )
            SetIntroScreenMiddleContent(
                text = "Manage your tasks",
                image = R.drawable.intro_image_1,
                description = "You can easily manage all of your daily tasks in DoMe for free"
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 62.dp)
            ) {
                Text(
                    text = "BACK",
                    fontSize = 16.sp,
                    color = Color(0x70ffffff),
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate(Screen.OnoardingScreen.route)
                    })
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { navController.navigate(route = Screen.SecondIntroScreen.route) }) {
                    Text(text = "NEXT", fontSize = 16.sp, color = Color(0xffffffff))
                }
            }
        }
    }
}

@Composable
fun SecondIntroScreen(
    navController: NavHostController
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color(0xFF121212))
        ) {
            Text(
                text = "SKIP",
                fontSize = 16.sp,
                color = Color(0x70ffffff),
                modifier = Modifier
                    .padding(top = 25.dp, start = 24.dp)
                    .clickable(onClick = { navController.navigate(Screen.DashBoardScreen.route) })
            )
            SetIntroScreenMiddleContent(
                text = "Create daily routine",
                image = R.drawable.intro_image_2,
                description = "In Uptodo  you can create your personalized routine to stay productive"
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 62.dp)
            ) {
                Text(
                    text = "BACK",
                    fontSize = 16.sp,
                    color = Color(0x70ffffff),
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate(Screen.FirstIntroScreen.route)
                    })
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { navController.navigate(route = Screen.ThirdIntroScreen.route) }) {
                    Text(text = "NEXT", fontSize = 16.sp, color = Color(0xffffffff))
                }
            }
        }
    }
}

@Composable
fun ThirdIntroScreen(
    navController: NavHostController
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color(0xFF121212))
        ) {
            Text(
                text = "SKIP",
                fontSize = 16.sp,
                color = Color(0x70ffffff),
                modifier = Modifier
                    .padding(top = 25.dp, start = 24.dp)
                    .clickable(onClick = { navController.navigate(Screen.DashBoardScreen.route) })
            )
            SetIntroScreenMiddleContent(
                text = "Orgonaize your tasks",
                image = R.drawable.intro_image_3,
                description = "You can organize your daily tasks by adding your tasks into separate categories"
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 62.dp)
            ) {
                Text(
                    text = "BACK",
                    fontSize = 16.sp,
                    color = Color(0x70ffffff),
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate(Screen.SecondIntroScreen.route)
                    })
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { navController.navigate(route = Screen.WelcomeScreen.route) }) {
                    Text(text = "GET STARTED", fontSize = 16.sp, color = Color(0xffffffff))
                }
            }
        }
    }
}


@Composable
fun SetIntroScreenMiddleContent(image: Int, text: String, description: String) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = ImageVector.vectorResource(image),
            contentDescription = "Image",
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = text,
            fontSize = 32.sp,
            color = Color(0xdeffffff),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(42.dp))
        Text(
            text = description,
            fontSize = 16.sp,
            color = Color(0xdeffffff),
            maxLines = 2,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}