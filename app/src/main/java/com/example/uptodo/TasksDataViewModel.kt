package com.example.uptodo

import android.net.Uri
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uptodo.Dialogues.CategotyItem


class TasksDataViewModel : ViewModel() {
    data class SettingsOptions(val icon: Int, val text: String, val modifier: Modifier)
    data class AccountOptions(val icon: Int, val text: String, val modifier: Modifier.Companion)
    data class UpToDoOptions(val icon: Int, val text: String, val modifier: Modifier.Companion)

    var settingsOptions = mutableListOf(
        TasksDataViewModel.SettingsOptions(
            icon = R.drawable.settings_icon,
            text = "App Settings",
            modifier = Modifier
        )
    )
    val accountOptions = mutableListOf(
        TasksDataViewModel.AccountOptions(
            icon = R.drawable.account_icon,
            text = "Change account name",
            modifier = Modifier
        ),
        TasksDataViewModel.AccountOptions(
            icon = R.drawable.key_icon,
            text = "Change account password",
            modifier = Modifier
        ),
        TasksDataViewModel.AccountOptions(
            icon = R.drawable.camera_icon,
            text = "Change account Image",
            modifier = Modifier
        ),
    )
    val upToDoOptions = mutableListOf(
        TasksDataViewModel.UpToDoOptions(
            icon = R.drawable.menu_icon,
            text = "About US",
            modifier = Modifier
        ),
        TasksDataViewModel.UpToDoOptions(
            icon = R.drawable.info_circle_icon,
            text = "FAQ",
            modifier = Modifier
        ),
        TasksDataViewModel.UpToDoOptions(
            icon = R.drawable.flash_icon,
            text = "Help & Feedback",
            modifier = Modifier
        ),
        TasksDataViewModel.UpToDoOptions(
            icon = R.drawable.like_icon,
            text = "Support US",
            modifier = Modifier
        ),
        TasksDataViewModel.UpToDoOptions(
            icon = R.drawable.logout_icon,
            text = "Log out",
            modifier = Modifier
        )
    )

    val categoryItems = mutableListOf(CategotyItem(
        image = R.drawable.grocery_icon,
        text = "Grocery",
        color = Color(0xffccff80)
    ),CategotyItem(
        image = R.drawable.work_icon,
        text = "Work",
        color = Color(0xffff9680)
    ),CategotyItem(
        image = R.drawable.sport_icon,
        text = "Sport",
        color = Color(0xff80ffff)
    ),CategotyItem(
        image = R.drawable.design_icon,
        text = "Design",
        color = Color(0xff80ffd9)
    ),CategotyItem(
        image = R.drawable.university_icon,
        text = "University",
        color = Color(0xff809cff)
    ),CategotyItem(
        image = R.drawable.social_icon,
        text = "Social",
        color = Color(0xffff80eb)
    ),CategotyItem(
        image = R.drawable.music_icon,
        text = "Music",
        color = Color(0xfffc80ff)
    ),CategotyItem(
        image = R.drawable.health_icon,
        text = "Health",
        color = Color(0xff80ffa3)
    ),CategotyItem(
        image = R.drawable.movie_icon,
        text = "Movie",
        color = Color(0xff80d1ff)
    ),CategotyItem(
        image = R.drawable.category_home_icon,
        text = "Home",
        color = Color(0xffff8080)
    ),CategotyItem(
        image = R.drawable.create_new_icon,
        text = "Create New",
        color = Color(0xffccff80)
    ))


    val taskData = mutableListOf<Task>()

    val profilePicture: MutableLiveData<Uri> = MutableLiveData()

}