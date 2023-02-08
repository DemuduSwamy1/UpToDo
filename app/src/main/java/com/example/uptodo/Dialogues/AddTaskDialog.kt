package com.example.uptodo.Dialogues

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.uptodo.OnBoardingScreens.TitleAndEditTextField
import com.example.uptodo.R
import com.example.uptodo.Task
import com.example.uptodo.TasksDataViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTaskDialog(
    tasksDataViewModel: TasksDataViewModel,
    navController: NavController,
    setAddTaskShowDialog: (Boolean) -> Unit
) {
    val task = remember {
        mutableStateOf(String())
    }
    val description = remember {
        mutableStateOf(String())
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
    if(showDatePicker.value){
        DatePicker(setDatePicker = {
            showDatePicker.value = it
        })
    }



    val showChooseCategoryDialog = remember { mutableStateOf(false) }
    val categoryItem = remember {
        mutableStateOf(CategotyItem(R.drawable.work_icon, "Work", Color(0xffff9680)))
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
        properties = DialogProperties(usePlatformDefaultWidth = false)
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
                                tasksDataViewModel.taskData.add(
                                    Task(
                                        task = task.value,
                                        description = description.value,
                                        time = "Today " + getCurrentTime(),
                                        category = CategotyItem(
                                            categoryItem.value.image,
                                            categoryItem.value.text,
                                            categoryItem.value.color
                                        ),
                                        priority = priority.value.toString()
                                    )
                                )
                                setAddTaskShowDialog(false)
//                                navController.navigate(Screen.IndexScreen.route)
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

@Composable
fun DatePicker(setDatePicker: (Boolean) -> Unit) {

    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )
    mDatePickerDialog.show()
}

