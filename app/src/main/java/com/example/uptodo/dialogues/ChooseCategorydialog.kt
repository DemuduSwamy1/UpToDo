package com.example.uptodo.dialogues

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
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.uptodo.CategoryItem
import com.example.uptodo.TasksDataViewModel
import com.example.uptodo.ui.theme.CreateNewCategoryDialog


@Composable
fun ChooseCategoryDialog(
    navController: NavController,
    setCategoryShowDialog: (Boolean) -> Unit,
    categotyItem: (CategoryItem) -> Unit,
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
    Dialog(
        onDismissRequest = { setCategoryShowDialog(false) },
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
                    categoryItems[0].color?.let {
                        CategoryItemUI(
                            categoryItems[0].image ?: 0,
                            categoryItems[0].text ?: "",
                            it
                        ) {
                            categotyItem(it)
                        }
                    }
                    CategoryItemUI(
                        categoryItems[1].image ?: 0,
                        categoryItems[1].text ?: "",
                        categoryItems[1].color!!
                    ) {
                        categotyItem(it)
                    }
                    CategoryItemUI(
                        categoryItems[2].image ?: 0,
                        categoryItems[2].text ?: "",
                        categoryItems[2].color!!
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
                        categoryItems[3].image ?: 0,
                        categoryItems[3].text ?: "", categoryItems[3].color!!
                    ) {
                        categotyItem(it)
                    }
                    CategoryItemUI(
                        categoryItems[4].image ?: 0,
                        categoryItems[4].text ?: "",
                        categoryItems[4].color!!
                    ) {
                        categotyItem(it)
                    }
                    CategoryItemUI(
                        categoryItems[5].image ?: 0,
                        categoryItems[5].text ?: "",
                        categoryItems[5].color!!
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
                        categoryItems[6].image ?: 0,
                        categoryItems[6].text ?: "",
                        categoryItems[6].color!!
                    ) {
                        categotyItem(it)
                    }
                    CategoryItemUI(
                        categoryItems[7].image ?: 0,
                        categoryItems[7].text ?: "",
                        categoryItems[7].color!!
                    ) {
                        categotyItem(it)
                    }
                    CategoryItemUI(
                        categoryItems[8].image ?: 0,
                        categoryItems[8].text ?: "",
                        categoryItems[8].color!!
                    ) {
                        categotyItem(it)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CategoryItemUI(
                        categoryItems[9].image ?: 0,
                        categoryItems[9].text ?: "",
                        categoryItems[9].color!!
                    ) {
                        categotyItem(it)
                    }
                    Spacer(modifier = Modifier.width(49.dp))
                    CategoryItemUI(
                        categoryItems[10].image ?: 0,
                        categoryItems[10].text ?: "",
                        categoryItems[10].color!!
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
    categoryImageValue: (CategoryItem) -> Unit,
) {
    Column(modifier = Modifier.clickable(onClick = {
        categoryImageValue.invoke(CategoryItem(categoryImage, categoryName, color))
    }), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            imageVector = ImageVector.vectorResource(id = categoryImage),
            contentDescription = "icon"
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = categoryName, fontSize = 16.sp, color = Color(0xdeffffff))
    }
}