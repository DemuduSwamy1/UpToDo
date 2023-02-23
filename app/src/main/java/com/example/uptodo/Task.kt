package com.example.uptodo

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName


data class Task(
    @SerializedName("task")
    var task: String? = "",
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("time")
    var time: String? = "",
    @SerializedName("category")
    val category: CategoryItem?, //= CategoryItem(R.drawable.university_icon, "", Color(0xffccff80)),
    @SerializedName("priority")
    var priority: String? = "",
    @SerializedName("id")
    var id: String? = ""
) {
    constructor() : this("", "", "", null, "", "")
}

data class CategoryItem(
    @SerializedName("image")
    val image: Int? = 0,
    @SerializedName("text")
    var text: String? = "",
    @SerializedName("color")
    val color: Color?
) {
    constructor() : this(0, "", Color(0xffff9680)) {

    }
}

