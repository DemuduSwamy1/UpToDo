package com.example.uptodo.OnBoardingScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.uptodo.Navigation.Screen
import com.example.uptodo.R

@Composable
fun SignUpScreen(navController: NavHostController) {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .background(Color(0xFF121212))
                .fillMaxSize()
                .padding(top = 24.dp, start = 24.dp, end = 24.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.arrow_left),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.clickable(onClick = {
                    navController.navigate(Screen.WelcomeScreen.route)
                })
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Register", fontSize = 32.sp, color = Color(0xdeffffff))
                Spacer(modifier = Modifier.height(23.dp))

                TitleAndEditTextField(title = "Username", placeHolder = "Enter your Username",textFieldColour = Color(0xff1d1d1d), keyboardType = KeyboardType.Text){
                    val value = it
                }

                Spacer(modifier = Modifier.height(25.dp))

                TitleAndEditTextField(title = "Password", placeHolder = "• • • • • • • • • • ",textFieldColour = Color(0xff1d1d1d), keyboardType = KeyboardType.Password){
                    val value = it
                }

                Spacer(modifier = Modifier.height(25.dp))

                TitleAndEditTextField(
                    title = "Confirm Password",
                    placeHolder = "• • • • • • • • • • ",
                    textFieldColour = Color(0xff1d1d1d), keyboardType = KeyboardType.Password){
                    val value = it
                }

                Spacer(modifier = Modifier.height(33.dp))

                Button(
                    onClick = { navController.navigate(Screen.DashBoardScreen.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0x808687e7))
                ) {
                    Text(text = "Register", color = Color(0x80ffffff), fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(30.dp))

                CustomDevider()

                Spacer(modifier = Modifier.height(30.dp))

                LoginTypes()

                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp), horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Don’t have an account?",
                        color = Color(0xFF979797),
                        fontSize = 12.sp
                    )
                    Text(text = "Register", color = Color(0xdeffffff), fontSize = 12.sp)
                }

            }
        }
    }
}

@Composable
fun CustomDevider(){
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .height(1.dp)
                .background(Color(0xFF979797))
                .weight(1f),

            )
        Spacer(modifier = Modifier.width(3.dp))
        Text(text = "or", color = Color(0xFF979797), fontSize = 16.sp)
        Spacer(modifier = Modifier.width(3.dp))
        Box(
            modifier = Modifier
                .height(1.dp)
                .background(Color(0xFF979797))
                .weight(1f)
        )
    }
}
