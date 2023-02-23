package com.example.uptodo

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.uptodo.dialogues.EditTaskDialog


@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IndexScreen(navController: NavHostController, tasksDataViewModel: TasksDataViewModel) {
    tasksDataViewModel.getTaskDataFromFirebase()
    val profileImage =
        if (tasksDataViewModel.profilePicture.value != null) tasksDataViewModel.profilePicture.value else R.drawable.profile_icon
    SetStatusBarColour(bottomNavbarColour = Color(0xFF363636))
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color(0xFF121212))
                .padding(top = 28.dp, start = 24.dp, end = 24.dp, bottom = 30.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.vuesax_outline_sort),
                    tint = Color(0xdeffffff),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Index", color = Color(0xdeffffff), fontSize = 20.sp)
                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = rememberImagePainter(data = profileImage),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                )
            }
            SetTasksData(
                navController = navController,
                tasksDataViewModel = tasksDataViewModel,
            )

        }
    }
}

@Composable
fun SetContentWhenTasksEmpty() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 111.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.checklist_icon),
            contentDescription = "DashBoardCheckListIcon"
        )
        Text(
            text = "What do you want to do today?",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color(0xdeffffff)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Tap + to add your tasks",
            fontSize = 16.sp,
            color = Color(0xdeffffff),
            textAlign = TextAlign.Center
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetTasksData(
    navController: NavHostController,
    tasksDataViewModel: TasksDataViewModel
) {
    val setEditTaskDialog = remember {
        mutableStateOf(false)
    }
    if (setEditTaskDialog.value) {
        EditTaskDialog(navController, tasksDataViewModel) {
            setEditTaskDialog.value = it
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .height(31.dp)
                    .width(76.dp)
                    .background(Color(0x35ffffff)),
                contentAlignment = Alignment.Center
            ) {
                Row() {
                    Text(text = "Today", color = Color(0xdeffffff), fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.arrow_down_icon),
                        contentDescription = "Down arrow"
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            if (tasksDataViewModel.taskData.value.isEmpty()) {
                SetContentWhenTasksEmpty()
            }
        }
        tasksDataViewModel.taskData.value.let {
            itemsIndexed(it.toMutableList()) { index, taskItem ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable {
                            tasksDataViewModel.taskDeletePosition.value = index
                            setEditTaskDialog.value = true
                        },
                    color = Color(0x35ffffff)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, bottom = 4.dp, top = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .border(
                                    shape = CircleShape,
                                    width = 1.dp,
                                    color = Color(0xdeffffff)
                                )
                        )

                        Spacer(modifier = Modifier.padding(10.dp))

                        Column() {
                            taskItem.let {
                                Text(
                                    text = it?.task ?: "",
                                    fontSize = 16.sp,
                                    color = Color(0xdeffffff)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))

                            Row {
                                taskItem.let {
                                    Text(
                                        text = it?.time ?: "",
                                        color = Color(0xffafafaf),
                                        fontSize = 14.sp
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                Log.d("colour", taskItem?.category?.color.toString())

                                taskItem?.category?.let {
                                    Modifier
                                        .height(29.dp)
                                        .width(87.dp)
                                        .background(it.color!!)
                                }?.let {
                                    Box(
                                        modifier = it,
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            taskItem.category.let {
                                                ImageVector.vectorResource(
                                                    id = it.image ?: 0
                                                )
                                            }
                                                .let {
                                                    Image(
                                                        imageVector = it,
                                                        contentDescription = "University_icon"
                                                    )
                                                }
                                            Text(
                                                text = taskItem.category.text ?: "",
                                                color = Color(0xffffffff),
                                                fontSize = 12.sp
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Box(
                                    modifier = Modifier
                                        .height(29.dp)
                                        .width(42.dp)
                                        .background(Color.Transparent)
                                        .border(
                                            border = BorderStroke(
                                                width = 1.dp,
                                                color = Color(0xff8687e7)
                                            )
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            imageVector = ImageVector.vectorResource(id = R.drawable.small_flag_icon),
                                            contentDescription = "Flag_icon"
                                        )
                                        Spacer(modifier = Modifier.width(5.dp))
                                        taskItem.let {
                                            Text(
                                                text = it?.priority ?: "",
                                                color = Color(0xffffffff),
                                                fontSize = 12.sp
                                            )
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }

}