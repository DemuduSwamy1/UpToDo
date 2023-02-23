package com.example.uptodo.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.uptodo.dialogues.ChangeAccountNameDialog
import com.example.uptodo.dialogues.ChangePasswordDialog
import com.example.uptodo.navigation.Screen
import com.example.uptodo.R
import com.example.uptodo.TasksDataViewModel

@Composable
fun ProfileScreen(navController: NavHostController, tasksDataViewModel: TasksDataViewModel) {
    val setChangeAccountNameDialog = remember {
        mutableStateOf(false)
    }
    val setChangePasswordDialog = remember {
        mutableStateOf(false)
    }
    val setAddProfileDialog = remember {
        mutableStateOf(false)
    }
    if (setAddProfileDialog.value) {
        ChangeProfileDialog(setChangeProfileDialog = {
            setAddProfileDialog.value = it
        }, tasksDataViewModel = tasksDataViewModel)
    }

    if (setChangeAccountNameDialog.value) {
        ChangeAccountNameDialog(setChangeAccountNameDialog = {
            setChangeAccountNameDialog.value = it
        })

    }


    if (setChangePasswordDialog.value) {
        ChangePasswordDialog {
            setChangePasswordDialog.value = it
        }
    }
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .background(Color(0xFF121212))
                .fillMaxSize()
                .padding(top = 29.dp, start = 24.dp, end = 24.dp)
        ) {

            SetProfileContent(tasksDataViewModel = tasksDataViewModel)

            LazyColumn {

                item {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = "Settings", fontSize = 14.sp, color = Color(0xffafafaf))
                    Spacer(modifier = Modifier.height(16.dp))
                }
                items(tasksDataViewModel.settingsOptions) { settingsOptionsItem ->
                    SetSettingsOptionsItem(
                        icon = settingsOptionsItem.icon,
                        text = settingsOptionsItem.text,
                    ) {
                        navController.navigate(Screen.SettingsScreen.route)
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(28.dp))
                    Text(text = "Account", fontSize = 14.sp, color = Color(0xffafafaf))
                    Spacer(modifier = Modifier.height(16.dp))
                }
                items(tasksDataViewModel.accountOptions) { accountOptionsItem ->
                    SetSettingsOptionsItem(
                        icon = accountOptionsItem.icon,
                        text = accountOptionsItem.text,
                    ) { accountOptionsItemText ->

                        when (accountOptionsItemText) {
                            tasksDataViewModel.accountOptions[0].text -> setChangeAccountNameDialog.value =
                                true
                            tasksDataViewModel.accountOptions[1].text -> setChangePasswordDialog.value =
                                true
                            tasksDataViewModel.accountOptions[2].text -> setAddProfileDialog.value =
                                true
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Uptodo", fontSize = 14.sp, color = Color(0xffafafaf))
                    Spacer(modifier = Modifier.height(16.dp))
                }
                items(tasksDataViewModel.upToDoOptions) { upToDoOptionsItem ->
                    SetSettingsOptionsItem(
                        icon = upToDoOptionsItem.icon,
                        text = upToDoOptionsItem.text,
                    ) {

                    }
                }

                item { Spacer(modifier = Modifier.height(100.dp)) }

            }

        }
    }
}


@Composable
fun SetSettingsOptionsItem(icon: Int, text: String, onClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable {
                onClick.invoke(text)
            }
    ) {
        Image(ImageVector.vectorResource(id = icon), contentDescription = "icon")
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = text, fontSize = 16.sp, color = Color(0xdeffffff))
        Spacer(modifier = Modifier.weight(1f))
        Image(
            ImageVector.vectorResource(id = R.drawable.arrow_right_icon),
            contentDescription = "Arrow right"
        )
    }
}

@Composable
fun SetProfileContent(tasksDataViewModel: TasksDataViewModel) {
    val profileImage =
        if (tasksDataViewModel.profilePicture.value != null) tasksDataViewModel.profilePicture.value else R.drawable.profile_icon
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Profile", color = Color(0xdeffffff), fontSize = 20.sp)
        Spacer(modifier = Modifier.height(24.dp))
        /*Image(
            painter = painterResource(id = R.drawable.profile_icon),
            contentDescription = "Profile",
            modifier = Modifier
                .clip(CircleShape)
                .size(85.dp)
        )*/
        Image(
            painter = rememberImagePainter(data = profileImage),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(85.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Martha Hays", color = Color(0xdeffffff), fontSize = 20.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF363636)),
                modifier = Modifier
                    .height(58.dp)
                    .weight(1f)
            ) {
                Text(text = "10 Task left", color = Color(0xdeffffff), fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.width(20.dp))

            OutlinedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF363636)),
                modifier = Modifier
                    .height(58.dp)
                    .weight(1f)
            ) {
                Text(text = "5 Task done", color = Color(0xdeffffff), fontSize = 16.sp)
            }
        }
    }
}


@Composable
fun ChangeProfileDialog(
    setChangeProfileDialog: (Boolean) -> Unit,
    tasksDataViewModel: TasksDataViewModel
) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
        tasksDataViewModel.profilePicture.value = uri
        setChangeProfileDialog(false)
    }
    Dialog(
        onDismissRequest = { setChangeProfileDialog(false) },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)
    ) {
        Surface(
            color = Color(0xFF363636),
            shape = RoundedCornerShape(10.dp),
            elevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Text(
                    text = "Change account Image",
                    fontSize = 16.sp,
                    color = Color(0xdeffffff),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(9.dp))
                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxSize()
                        .background(Color(0xFF979797))
                )
                Spacer(modifier = Modifier.height(27.dp))
                Column(modifier = Modifier.padding(start = 34.dp, bottom = 10.dp)) {
                    Text(
                        text = "Tack picture",
                        fontSize = 16.sp,
                        color = Color(0xdeffffff),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Import from gallery",
                        fontSize = 16.sp,
                        color = Color(0xdeffffff),
                        modifier = Modifier.clickable(onClick = {
                            launcher.launch("image/*")
                        }),
                    )
                }
            }
        }
    }
}