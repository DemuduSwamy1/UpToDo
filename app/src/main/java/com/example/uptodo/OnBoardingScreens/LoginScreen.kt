package com.example.uptodo.OnBoardingScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.uptodo.Navigation.Screen
import com.example.uptodo.R

@Composable
fun LoginScreen(navController: NavHostController) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .background(Color(0xFF121212))
                .fillMaxSize()
                .padding(top = 29.dp, start = 24.dp, end = 24.dp)
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
                Spacer(modifier = Modifier.height(41.dp))
                Text(text = "Login", fontSize = 32.sp, color = Color(0xdeffffff))
                Spacer(modifier = Modifier.height(53.dp))

                TitleAndEditTextField(
                    title = "Username",
                    placeHolder = "Enter your Username",
                    textFieldColour = Color(0xff1d1d1d),
                    keyboardType = KeyboardType.Text
                ) {
                    val value = it
                }

                Spacer(modifier = Modifier.height(25.dp))

                TitleAndEditTextField(
                    title = "Password",
                    placeHolder = "• • • • • • • • • • ",
                    textFieldColour = Color(0xff1d1d1d),
                    keyboardType = KeyboardType.Password
                ) {
                    val value = it
                }

                Spacer(modifier = Modifier.height(60.dp))
                Button(
                    onClick = { navController.navigate(Screen.DashBoardScreen.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0x808687e7))
                ) {
                    Text(text = "Login", color = Color(0x80ffffff), fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(35.dp))

                CustomDevider()

                Spacer(modifier = Modifier.height(35.dp))

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
fun TitleAndEditTextField(
    title: String,
    placeHolder: String,
    textFieldColour: Color,
    keyboardType: KeyboardType,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    text: (String) -> Unit
) {
    Text(text = title, fontSize = 16.sp, color = Color(0xdeffffff))
    Spacer(modifier = Modifier.height(8.dp))

    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    TextField(value = textFieldValue, onValueChange = { newText ->
        textFieldValue = newText
        text.invoke(textFieldValue.text)
    }, modifier = Modifier
        .fillMaxWidth()
        .height(52.dp)
        .background(textFieldColour)
        .border(1.dp, color = Color(0xFF979797)),
        colors = TextFieldDefaults.textFieldColors(Color(0xffffffff)),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            capitalization = capitalization
        ),
        placeholder = {
            Text(
                text = placeHolder,
                color = Color(0xFF535353),
                fontSize = 16.sp
            )
        })
}


@Composable
fun LoginTypes() {
    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        border = BorderStroke(1.5.dp, Color(0xff8875ff)),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff121212))
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.google_icon),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Login with Google", color = Color(0xdeffffff), fontSize = 16.sp)
    }
    Spacer(modifier = Modifier.height(20.dp))
    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        border = BorderStroke(1.5.dp, Color(0xff8875ff)),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff121212))
    ) {
        Row {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.apple_icon),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Login with Apple", color = Color(0xdeffffff), fontSize = 16.sp)
        }
    }
}
