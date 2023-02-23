package com.example.mybottomnavigation

import com.example.uptodo.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Index : BottomNavItem("Index", R.drawable.home_icon, "index")
    object Calendar : BottomNavItem("Calendar", R.drawable.calendar_icon, "calendar")
    object AddTask : BottomNavItem("", R.drawable.add_icon, "add_task")
    object Focus : BottomNavItem("Focus", R.drawable.focus_icon, "focus")
    object Profile : BottomNavItem("Profile", R.drawable.account_icon, "profie")
}
