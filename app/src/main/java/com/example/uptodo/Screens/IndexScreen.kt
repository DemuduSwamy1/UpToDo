package com.example.uptodo

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

@Composable
fun IndexScreen(navController: NavHostController, tasksDataViewModel: TasksDataViewModel) {
    val taskData = tasksDataViewModel.taskData
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
            if (taskData.size >= 1) {
                SetTasksData(taskData)
            } else {
                SetContentWhenTasksEmpty()
            }

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

@Composable
fun SetTasksData(taskData: MutableList<Task>) {
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
        }
        items(taskData) { taskItem ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
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
                            .border(shape = CircleShape, width = 1.dp, color = Color(0xdeffffff))
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    Column() {
                        Text(text = taskItem.task, fontSize = 16.sp, color = Color(0xdeffffff))
                        Spacer(modifier = Modifier.height(6.dp))

                        Row {
                            Text(
                                text = taskItem.time,
                                color = Color(0xffafafaf),
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Log.d("colour", taskItem.category.color.toString())

                            Box(
                                modifier = Modifier
                                    .height(29.dp)
                                    .width(87.dp)
                                    .background(taskItem.category.color),
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        imageVector = ImageVector.vectorResource(id = taskItem.category.image),
                                        contentDescription = "University_icon"
                                    )
                                    Text(
                                        text = taskItem.category.text,
                                        color = Color(0xffffffff),
                                        fontSize = 12.sp
                                    )
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
                                    Text(
                                        text = taskItem.priority,
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
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }

}