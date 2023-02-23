package com.example.uptodo.dialogues

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.example.uptodo.CategoryItem
import com.example.uptodo.R
import com.example.uptodo.TasksDataViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditTaskDialog(
    navController: NavHostController,
    tasksDataViewModel: TasksDataViewModel,
    setEditTaskDialog: (Boolean) -> Unit
) {
    val taskPosition = tasksDataViewModel.taskDeletePosition.value
    val taskInfo = tasksDataViewModel.taskData.value.get(taskPosition!!)

    val setEditTaskTitleDialog = remember {
        mutableStateOf(false)
    }
    if (setEditTaskTitleDialog.value) {
        EditTaskTitleDialog(tasksDataViewModel, setEditDialog = {
            setEditTaskTitleDialog.value = it
        })
    }

    val showDatePicker = remember {
        mutableStateOf(false)
    }
    if (showDatePicker.value) {
        CustomDatePickerDialog(onDateSelected = {
            taskInfo?.time = it.toString()
        }, onDismissRequest = {
            showDatePicker.value = it
        })
    }

    val showChooseCategoryDialog = remember { mutableStateOf(false) }
    if (showChooseCategoryDialog.value) {
        ChooseCategoryDialog(
            tasksDataViewModel = tasksDataViewModel,
            navController = navController,
            setCategoryShowDialog = {
                showChooseCategoryDialog.value = it
            },
            categotyItem = {
                taskInfo?.category?.text = it.text
            })
    }

    val showPriorityDialog = remember { mutableStateOf(false) }
    if (showPriorityDialog.value) {
        TaskPriorityDialog(setPriorityShowDialog = {
            showPriorityDialog.value = it
        }, priority = {
            taskInfo?.priority = it.toString()
        })
    }

    val showDeleteTaskDialog = remember {
        mutableStateOf(false)
    }
    if (showDeleteTaskDialog.value) {
        if (taskPosition != null) {
            DeleteTaskDialog(taskPosition, tasksDataViewModel, setDeleteTaskDialog = {
                showDeleteTaskDialog.value = it
            }, setEditTaskDialog)
        }
    }

    Dialog(
        onDismissRequest = { setEditTaskDialog(false) },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = false,
            dismissOnBackPress = true
        )
    ) {
        Surface() {
            Column(
                modifier = Modifier
                    .background(Color(0xFF121212))
                    .fillMaxSize()
                    .padding(top = 26.dp, start = 28.dp, bottom = 60.dp, end = 28.dp)
            ) {
                Row() {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xff1d1d1d))
                            .clickable(onClick = {
                                setEditTaskDialog.invoke(false)
                            }), contentAlignment = Alignment.Center
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.cancel_icon),
                            contentDescription = "Cancel_icon"
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xff1d1d1d)), contentAlignment = Alignment.Center
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.repeat_icon),
                            contentDescription = "Repeat_icon"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(34.dp))
                Row() {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .border(shape = CircleShape, width = 1.dp, color = Color(0xdeffffff))
                    )
                    Spacer(modifier = Modifier.width(21.dp))
                    Text(
                        text = taskInfo?.task ?: "",
                        fontSize = 20.sp,
                        color = Color(0xdeffffff),
                        modifier = Modifier.width(230.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.edit_icon),
                        contentDescription = "edit_icon",
                        modifier = Modifier.clickable(onClick = {
                            setEditTaskTitleDialog.value = true
                        })
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = taskInfo?.description ?: "",
                    fontSize = 16.sp,
                    color = Color(0xffafafaf),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(38.dp))
                SetTwoItemsAsHorizontal(
                    icon = R.drawable.timer_icon,
                    title = "Task Time :",
                    content = taskInfo?.time ?: "",
                    itemClicked = {
                        showDatePicker.value = true
                    }
                )
                Spacer(modifier = Modifier.height(52.dp))
                SetTaskCategoryHorizontalView(
                    icon = R.drawable.tag_icon,
                    title = "Task Category :",
                    categoryItem = CategoryItem(
                        R.drawable.university_icon,
                        text = taskInfo?.category?.text ?: "",
                        color = Color.Black
                    ),
                    itemClicked = {
                        showChooseCategoryDialog.value = true
                    }
                )
                Spacer(modifier = Modifier.height(44.dp))
                SetTwoItemsAsHorizontal(
                    icon = R.drawable.flag_icon,
                    title = "Task Priority :",
                    content = taskInfo?.priority ?: "",
                    itemClicked = {
                        showPriorityDialog.value = true
                    }
                )
                Spacer(modifier = Modifier.height(44.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = {
                            showDeleteTaskDialog.value = true
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.trash_icon),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Delete Task", fontSize = 16.sp, color = Color(0xffff4949))
                }
            }
        }
    }
}

@Composable
fun SetTwoItemsAsHorizontal(icon: Int, title: String, content: String, itemClicked: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(imageVector = ImageVector.vectorResource(id = icon), contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title, fontSize = 16.sp, color = Color(0xdeffffff))
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .background(Color(0x35ffffff))
                .clickable(onClick = { itemClicked() })
        ) {
            Text(
                text = content,
                fontSize = 12.sp,
                color = Color(0xdeffffff),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun SetTaskCategoryHorizontalView(
    icon: Int,
    title: String,
    categoryItem: CategoryItem,
    itemClicked: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(imageVector = ImageVector.vectorResource(id = icon), contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title, fontSize = 16.sp, color = Color(0xdeffffff))
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .background(Color(0x35ffffff))
                .clickable(onClick = {
                    itemClicked()
                })
        ) {
//            Image(imageVector = ImageVector.vectorResource(id = categotyItem.image), contentDescription = null )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = categoryItem.text ?: "",
                fontSize = 12.sp,
                color = Color(0xdeffffff),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
    }
}
