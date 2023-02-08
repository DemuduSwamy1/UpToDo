package com.example.uptodo.OnBoardingScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.uptodo.Navigation.Screen
import com.example.uptodo.R

@Composable
fun OnBoardingScreen(navController: NavHostController) {
    Scaffold() {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxSize()
                .padding(it)
                .background(Color(0xFF121212)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.logo_icon),
                contentDescription = "OnBoardingScreen logo",
            )
            Spacer(modifier = Modifier.padding(bottom = 30.dp))
            Button(onClick = { navController.navigate(Screen.FirstIntroScreen.route)}) {
                Text(text = "START", color = Color(0xffffffff), fontSize = 20.sp)
            }
        }

    }
}
