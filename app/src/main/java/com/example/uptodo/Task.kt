package com.example.uptodo

import androidx.compose.ui.graphics.Color
import com.example.uptodo.Dialogues.CategotyItem

data class Task(
    val task: String = "",
    val description: String = "",
    val time: String = "",
    val category: CategotyItem = CategotyItem(R.drawable.university_icon, "", Color(0xffccff80)),
    val priority: String = ""
)


//data class Data {
//    fun getDataList(): MutableList<Task> {
//        return taskData
//    }
//}

