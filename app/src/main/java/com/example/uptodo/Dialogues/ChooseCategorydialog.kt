package com.example.uptodo.Dialogues

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.uptodo.TasksDataViewModel
import com.example.uptodo.ui.theme.CreateNewCategoryDialog

data class CategotyItem(val image: Int, val text: String, val color: Color)

@Composable
fun ChooseCategoryDialog(
    navController: NavController,
    setCategoryShowDialog: (Boolean) -> Unit,
    categotyItem: (CategotyItem) -> Unit,
    tasksDataViewModel: TasksDataViewModel
) {
    val showNewCategoryDialog = remember {
        mutableStateOf(false)
    }
    if (showNewCategoryDialog.value) {
        CreateNewCategoryDialog(
            setNewCatgoryShowDialog = {
                showNewCategoryDialog.value = it
            })
    }
    val categoryItems = tasksDataViewModel.categoryItems
    Dialog(onDismissRequest = { setCategoryShowDialog(false) }) {
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
                    text = "Choose Category",
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
                Spacer(modifier = Modifier.height(21.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CategoryItemUI(
                        categoryItems[0].image,
                        categoryItems[0].text,
                        categoryItems[0].color
                    ) {
                        categotyItem(it)
                    }
                    CategoryItemUI(
                        categoryItems[1].image,
                        categoryItems[1].text,
                        categoryItems[1].color
                    ) {
                        categotyItem(it)
                    }
                    CategoryItemUI(
                        categoryItems[2].image,
                        categoryItems[2].text,
                        categoryItems[2].color
                    ) {
                        categotyItem(it)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CategoryItemUI(
                        categoryItems[3].image,
                        categoryItems[3].text, categoryItems[3].color
                    ) {
                        categotyItem(it)
                    }
                    CategoryItemUI(
                        categoryItems[4].image,
                        categoryItems[4].text,
                        categoryItems[4].color
                    ) {
                        categotyItem(it)
                    }
                    CategoryItemUI(
                        categoryItems[5].image,
                        categoryItems[5].text,
                        categoryItems[5].color
                    ) {
                        categotyItem(it)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CategoryItemUI(
                        categoryItems[6].image,
                        categoryItems[6].text,
                        categoryItems[6].color
                    ) {
                        categotyItem(it)
                    }
                    CategoryItemUI(
                        categoryItems[7].image,
                        categoryItems[7].text,
                        categoryItems[7].color
                    ) {
                        categotyItem(it)
                    }
                    CategoryItemUI(
                        categoryItems[8].image,
                        categoryItems[8].text,
                        categoryItems[8].color
                    ) {
                        categotyItem(it)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CategoryItemUI(
                        categoryItems[9].image,
                        categoryItems[9].text,
                        categoryItems[9].color
                    ) {
                        categotyItem(it)
                    }
                    Spacer(modifier = Modifier.width(49.dp))
                    CategoryItemUI(
                        categoryItems[10].image,
                        categoryItems[10].text,
                        categoryItems[10].color
                    ) {
                        showNewCategoryDialog.value = true
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {
                        setCategoryShowDialog(false)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff8687e7))
                ) {
                    Text(text = "Add Category", color = Color(0xffffffff), fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun CategoryItemUI(
    categoryImage: Int,
    categoryName: String,
    color: Color,
    categoryImageValue: (CategotyItem) -> Unit,
) {
    Column(modifier = Modifier.clickable(onClick = {
        categoryImageValue.invoke(CategotyItem(categoryImage, categoryName, color))
    }), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            imageVector = ImageVector.vectorResource(id = categoryImage),
            contentDescription = "icon"
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = categoryName, fontSize = 16.sp, color = Color(0xdeffffff))
    }
}