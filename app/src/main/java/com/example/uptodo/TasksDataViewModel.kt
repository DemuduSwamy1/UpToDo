package com.example.uptodo

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.UUID


class TasksDataViewModel() : ViewModel() {
    var userEmail = mutableStateOf(String())
    val userUid = mutableStateOf(String())

    private var _taskData = mutableStateOf<List<Task>>(emptyList())
    val taskData: State<List<Task>> = _taskData

    val profilePicture: MutableLiveData<Uri> = MutableLiveData()

    var taskDeletePosition = MutableLiveData<Int>()

    fun deleteTask() {
        taskDeletePosition.value?.let { taskData.value.drop(it) }
    }

    fun addTaskIntoDatabase(uid: String, task: Task) {
        val uniqueId = UniqueId(task.task ?: "", 123, task.description ?: "").UniqueId
        val database = Firebase.database.reference
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                database.child("users").child(uid).child("tasks").child(uniqueId.toString())
                    .setValue(task)
            }

            override fun onCancelled(error: DatabaseError) {
                /*TODO("Not yet implemented")*/
            }
        })

    }

    fun getTaskDataFromFirebase() {
        val firebaseDatabase = FirebaseDatabase.getInstance();
        val databaseReference =
            firebaseDatabase.reference.child("users").child(userUid.value)
                .child("tasks")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val typeIndicator = object : GenericTypeIndicator<HashMap<String, Task>>() {}
                val snapshotOfData = snapshot.getValue(typeIndicator)
                if (snapshotOfData?.values != null) {
                    _taskData.value = snapshotOfData.values.toMutableStateList()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    data class SettingsOptions(val icon: Int, val text: String, val modifier: Modifier)
    data class AccountOptions(val icon: Int, val text: String, val modifier: Modifier.Companion)
    data class UpToDoOptions(val icon: Int, val text: String, val modifier: Modifier.Companion)

    var settingsOptions = mutableListOf(
        SettingsOptions(
            icon = R.drawable.settings_icon,
            text = "App Settings",
            modifier = Modifier
        )
    )
    val accountOptions = mutableListOf(
        AccountOptions(
            icon = R.drawable.account_icon,
            text = "Change account name",
            modifier = Modifier
        ),
        AccountOptions(
            icon = R.drawable.key_icon,
            text = "Change account password",
            modifier = Modifier
        ),
        AccountOptions(
            icon = R.drawable.camera_icon,
            text = "Change account Image",
            modifier = Modifier
        ),
    )
    val upToDoOptions = mutableListOf(
        UpToDoOptions(
            icon = R.drawable.menu_icon,
            text = "About US",
            modifier = Modifier
        ),
        UpToDoOptions(
            icon = R.drawable.info_circle_icon,
            text = "FAQ",
            modifier = Modifier
        ),
        UpToDoOptions(
            icon = R.drawable.flash_icon,
            text = "Help & Feedback",
            modifier = Modifier
        ),
        UpToDoOptions(
            icon = R.drawable.like_icon,
            text = "Support US",
            modifier = Modifier
        ),
        UpToDoOptions(
            icon = R.drawable.logout_icon,
            text = "Log out",
            modifier = Modifier
        )
    )

    val categoryItems = mutableListOf(
        CategoryItem(
            image = R.drawable.grocery_icon,
            text = "Grocery",
            color = Color(0xffccff80)
        ), CategoryItem(
            image = R.drawable.work_icon,
            text = "Work",
            color = Color(0xffff9680)
        ), CategoryItem(
            image = R.drawable.sport_icon,
            text = "Sport",
            color = Color(0xff80ffff)
        ), CategoryItem(
            image = R.drawable.design_icon,
            text = "Design",
            color = Color(0xff80ffd9)
        ), CategoryItem(
            image = R.drawable.university_icon,
            text = "University",
            color = Color(0xff809cff)
        ), CategoryItem(
            image = R.drawable.social_icon,
            text = "Social",
            color = Color(0xffff80eb)
        ), CategoryItem(
            image = R.drawable.music_icon,
            text = "Music",
            color = Color(0xfffc80ff)
        ), CategoryItem(
            image = R.drawable.health_icon,
            text = "Health",
            color = Color(0xff80ffa3)
        ), CategoryItem(
            image = R.drawable.movie_icon,
            text = "Movie",
            color = Color(0xff80d1ff)
        ), CategoryItem(
            image = R.drawable.category_home_icon,
            text = "Home",
            color = Color(0xffff8080)
        ), CategoryItem(
            image = R.drawable.create_new_icon,
            text = "Create New",
            color = Color(0xffccff80)
        )
    )
}

class UniqueId(
    private val playerName: String,
    private val playerSkill: Int,
    private val playerType: String,
    val UniqueId: UUID = UUID.randomUUID(),
)
