package com.example.uptodo.Dialogues

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.uptodo.OnBoardingScreens.TitleAndEditTextField

@Composable
fun ChangePasswordDialog(setChangepasswordDialog: (Boolean) -> Unit) {
    Dialog(onDismissRequest = { setChangepasswordDialog(false) }) {
        Surface(
            color = Color(0xFF363636),
            shape = RoundedCornerShape(10.dp),
            elevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = "Change account Password",
                    fontSize = 16.sp,
                    color = Color(0xdeffffff),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(9.dp))
                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxSize()
                        .background(Color(0xFF979797))
                )
                Spacer(modifier = Modifier.height(9.dp))
                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                    TitleAndEditTextField(
                        title = "Enter old password",
                        placeHolder = "• • • • • • • • • • ",
                        textFieldColour = Color.Transparent,
                        keyboardType = KeyboardType.Password
                    ) {
                        val value = it
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    TitleAndEditTextField(
                        title = "Enter new password",
                        placeHolder = "• • • • • • • • • • ",
                        textFieldColour = Color.Transparent,
                        keyboardType = KeyboardType.Password
                    ) {
                        val value = it
                    }

                }
                Spacer(modifier = Modifier.height(37.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedButton(
                        onClick = { setChangepasswordDialog(false) },
                        modifier = Modifier
                            .height(48.dp)
                            .weight(1f),

                        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                        border = BorderStroke(width = 0.dp, color = Color.Transparent)

                    ) {
                        Text(text = "Cancel", color = Color(0xff8687e7), fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .height(48.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff8687e7))
                    ) {
                        Text(text = "Save", color = Color(0xffffffff), fontSize = 16.sp)
                    }
                }
            }
        }
    }
}