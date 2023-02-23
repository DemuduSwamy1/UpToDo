package com.example.uptodo.dialogues

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.uptodo.TasksDataViewModel

@Composable
fun EditTaskTitleDialog(tasksDataViewModel: TasksDataViewModel, setEditDialog: (Boolean) -> Unit) {
    val taskPosition = tasksDataViewModel.taskDeletePosition.value
    val taskInfo = taskPosition?.let { tasksDataViewModel.taskData.value?.get(it) }
    Dialog(
        onDismissRequest = { setEditDialog(false) },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)
    ) {
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
                    text = "Edit Task title",
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
                Spacer(modifier = Modifier.height(16.dp))

                var taskTitle by remember { mutableStateOf(TextFieldValue(taskInfo?.task ?: "")) }
                TextField(
                    value = taskTitle,
                    onValueChange = { newName ->
                        taskTitle = newName
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(horizontal = 10.dp)
                        .background(Color(0xFF363636))
                        .border(1.dp, color = Color(0xFF979797)),
                    colors = TextFieldDefaults.textFieldColors(Color(0xdeffffff)),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )

                Spacer(modifier = Modifier.height(16.dp))

                var taskDescription by remember {
                    mutableStateOf(
                        TextFieldValue(
                            taskInfo?.description ?: ""
                        )
                    )
                }
                TextField(
                    value = taskDescription, onValueChange = { newName ->
                        taskDescription = newName
                    }, singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(horizontal = 10.dp)
                        .background(Color(0xFF363636))
                        .border(1.dp, color = Color(0xFF979797)),
                    colors = TextFieldDefaults.textFieldColors(Color(0xdeffffff)),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )


                Spacer(modifier = Modifier.height(28.dp))


                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedButton(
                        onClick = { setEditDialog(false) },
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
                            taskInfo?.task = taskTitle.text
                            taskInfo?.description = taskDescription.text
                            setEditDialog.invoke(false)
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