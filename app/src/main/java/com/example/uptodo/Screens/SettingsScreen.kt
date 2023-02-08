package com.example.uptodo.OnBoardingScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.uptodo.R
import com.example.uptodo.Screens.SetSettingsOptionsItem

data class AppSettingsOptions(val icon: Int, val text: String)
data class ImportOptions(val icon: Int, val text: String)

@Composable
fun SettingsScreen(navController: NavHostController) {
    val appSettingsOptions = mutableListOf<AppSettingsOptions>()
    val importOptions = mutableListOf<ImportOptions>()
    appSettingsOptions.add(
        AppSettingsOptions(
            icon = R.drawable.brush_icon,
            text = "Change app color"
        )
    )
    appSettingsOptions.add(
        AppSettingsOptions(
            icon = R.drawable.text_icon,
            text = "Change app typography"
        )
    )
    appSettingsOptions.add(
        AppSettingsOptions(
            icon = R.drawable.language_square_icon,
            text = "Change app language"
        )
    )
    importOptions.add(
        ImportOptions(
            icon = R.drawable.import_icon,
            text = "Import from Google calendar"
        )
    )
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .background(Color(0xFF121212))
                .fillMaxSize()
                .padding(top = 29.dp, start = 24.dp, end = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_left),
                    contentDescription = "Left Arrow",
                    alignment = Alignment.CenterStart,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Settings",
                    color = Color(0xdeffffff),
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1.5f),
                )
            }

            Spacer(modifier = Modifier.height(21.dp))
            Text(text = "Settings", fontSize = 14.sp, color = Color(0xffafafaf))
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(appSettingsOptions) { appSettingsOptionsItem ->
                    SetSettingsOptionsItem(
                        icon = appSettingsOptionsItem.icon,
                        text = appSettingsOptionsItem.text){

                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Import", fontSize = 14.sp, color = Color(0xffafafaf))
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(importOptions) { importOptionsItem ->
                    SetSettingsOptionsItem(
                        icon = importOptionsItem.icon,
                        text = importOptionsItem.text,
                    ){

                    }
                }
            }
        }
    }
}