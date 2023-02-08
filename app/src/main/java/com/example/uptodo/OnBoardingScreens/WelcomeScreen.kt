package com.example.uptodo.OnBoardingScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
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
fun WelcomeScreen(navController: NavHostController) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .background(Color(0xFF121212))
                .fillMaxSize()
                .padding(top = 29.dp, start = 23.dp, end = 23.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.arrow_left),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.clickable(onClick = {
                    navController.navigate(Screen.ThirdIntroScreen.route)
                })
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 67.dp)
            ) {
                Spacer(modifier = Modifier.height(58.dp))
                Text(text = "Welcome to UpTodo", fontSize = 32.sp, color = Color.White)
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Please login to your account or create\n new account to continue",
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color(0xabffffff),
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { navController.navigate(route = Screen.LoginScreen.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff8875ff))
                ) {
                    Text(text = "LOGIN", color = Color(0xffffffff), fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(28.dp))
                OutlinedButton(
                    onClick = { navController.navigate(route = Screen.SignUpScreen.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    border = BorderStroke(1.5.dp, Color(0xff8875ff)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff121212))
                ) {
                    Text(text = "CREATE ACCOUNT", color = Color(0xffffffff), fontSize = 16.sp)
                }
            }
        }
    }
}
