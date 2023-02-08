package com.example.uptodo.Dialogues

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.uptodo.R

@Composable
fun TaskPriorityDialog(
    setPriorityShowDialog: (Boolean) -> Unit,
    priority: (Int) -> Unit,
) {
    Dialog(onDismissRequest = { setPriorityShowDialog(false) }) {
        Surface(
            color = Color(0xFF363636),
            shape = RoundedCornerShape(10.dp),
            elevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Text(
                    text = "Task Priority",
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
                Spacer(modifier = Modifier.height(21.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TaskPriorityItem(priority = 1) {
                        priority(it)
                    }
                    TaskPriorityItem(priority = 2) {
                        priority(it)
                    }
                    TaskPriorityItem(priority = 3) {
                        priority(it)
                    }
                    TaskPriorityItem(priority = 4) {
                        priority(it)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TaskPriorityItem(priority = 5) {
                        priority(it)
                    }
                    TaskPriorityItem(priority = 6) {
                        priority(it)
                    }
                    TaskPriorityItem(priority = 7) {
                        priority(it)
                    }
                    TaskPriorityItem(priority = 8) {
                        priority(it)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TaskPriorityItem(priority = 9) {
                        priority(it)
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    TaskPriorityItem(priority = 10) {
                        priority(it)
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedButton(
                        onClick = {
                            setPriorityShowDialog(false)
                        },
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
                        onClick = {
                            setPriorityShowDialog(false)
                        },
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

@Composable
fun TaskPriorityItem(priority: Int, priorityNumber: (Int) -> Unit) {
    var color by remember {
        mutableStateOf(Color(0xFF272727))
    }
    println("$priority $priorityNumber")
    Box(
        modifier = Modifier
            .size(64.dp)
            .background(color = color)
            .clickable(onClick = {
                color = Color(0xff8687e7)
                priorityNumber.invoke(priority)
            }), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.flag_icon),
                contentDescription = "Flag icon"
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = priority.toString(), fontSize = 16.sp, color = Color(0xdeffffff))
        }
    }
}