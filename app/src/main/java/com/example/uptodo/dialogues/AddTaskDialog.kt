package com.example.uptodo.dialogues

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.uptodo.CategoryItem
import com.example.uptodo.R
import com.example.uptodo.Task
import com.example.uptodo.TasksDataViewModel
import com.example.uptodo.onBoardingScreens.TitleAndEditTextField
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTaskDialog(
    context: Context,
    tasksDataViewModel: TasksDataViewModel,
    navController: NavController,
    setAddTaskShowDialog: (Boolean) -> Unit,
) {
    val task = remember {
        mutableStateOf(String())
    }
    val description = remember {
        mutableStateOf(String())
    }
    val date = remember {
        mutableStateOf(LocalDate.now())
    }

    val showPriorityDialog = remember { mutableStateOf(false) }
    val priority = remember { mutableStateOf(0) }
    if (showPriorityDialog.value) {
        TaskPriorityDialog(setPriorityShowDialog = {
            showPriorityDialog.value = it
        }, priority = {
            priority.value = it
        })
    }

    val showDatePicker = remember {
        mutableStateOf(false)
    }
    if (showDatePicker.value) {
        CustomDatePickerDialog(onDateSelected = {
            date.value = it
        }, onDismissRequest = {
            showDatePicker.value = it
        })
    }


    val showChooseCategoryDialog = remember { mutableStateOf(false) }
    val categoryItem = remember {
        mutableStateOf(CategoryItem(R.drawable.work_icon, "Work", Color(0xffff9680)))
    }
    if (showChooseCategoryDialog.value) {
        ChooseCategoryDialog(
            tasksDataViewModel = tasksDataViewModel,
            navController = navController,
            setCategoryShowDialog = {
                showChooseCategoryDialog.value = it
            },
            categotyItem = {
                categoryItem.value = it
            })
    }


    Dialog(
        onDismissRequest = { setAddTaskShowDialog(false) },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            color = Color(0xFF363636),
            shape = RoundedCornerShape(10.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                TitleAndEditTextField(
                    title = "Add Task",
                    placeHolder = "Add task",
                    textFieldColour = Color(0xFF363636),
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences
                ) {
                    task.value = it
                }
                Spacer(modifier = Modifier.height(13.dp))
                TitleAndEditTextField(
                    title = "Description",
                    placeHolder = "Description",
                    textFieldColour = Color(0xFF363636),
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences
                ) {
                    description.value = it
                }
                Spacer(modifier = Modifier.height(17.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.timer_icon),
                        contentDescription = "TimerIcon",
                        modifier = Modifier
                            .padding(end = 30.dp)
                            .clickable(onClick = {
                                showDatePicker.value = true
                            })
                    )
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.tag_icon),
                        contentDescription = "TagIcon",
                        modifier = Modifier
                            .padding(end = 30.dp)
                            .clickable(
                                onClick = { showChooseCategoryDialog.value = true }
                            )
                    )
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.flag_icon),
                        contentDescription = "FlagIcon",
                        modifier = Modifier
                            .padding(end = 30.dp)
                            .clickable(onClick = {
                                showPriorityDialog.value = true
                            })
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.send_icon),
                        contentDescription = "SendIcon",
                        modifier = Modifier.clickable(
                            onClick = {
                                val task = Task(
                                    task = task.value,
                                    description = description.value,
                                    time = date.value.toString(),
                                    category = CategoryItem(
                                        image = categoryItem.value.image,
                                        text = categoryItem.value.text,
                                        color = categoryItem.value.color
                                    ),
                                    priority = priority.value.toString()
                                )
                                tasksDataViewModel.addTaskIntoDatabase(
                                    uid = tasksDataViewModel.userUid.value,
                                    task = task
                                )
                                setAddTaskShowDialog(false)
                            }
                        )
                    )

                }
            }
        }
    }
}


@SuppressLint("SimpleDateFormat")
fun getCurrentTime(): String {
//    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val sdf = SimpleDateFormat("hh:mm")
    val currentDate = sdf.format(Date())
    Log.d("C_DATE_is", currentDate)
    return currentDate
}

