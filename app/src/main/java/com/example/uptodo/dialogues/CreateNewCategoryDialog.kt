package com.example.uptodo.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.uptodo.onBoardingScreens.TitleAndEditTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateNewCategoryDialog(
    setNewCatgoryShowDialog: (Boolean) -> Unit,
) {
    Dialog(
        onDismissRequest = { setNewCatgoryShowDialog(false) },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = false,
            dismissOnBackPress = true
        )
    ) {
        Scaffold() {
            Column(
                modifier = Modifier
                    .padding(it)
                    .background(Color(0xFF121212))
                    .fillMaxSize()
                    .padding(top = 24.dp, start = 24.dp, bottom = 60.dp)
            ) {
                Text(text = "Create new category", fontSize = 20.sp, color = Color(0xdeffffff))
                Spacer(modifier = Modifier.height(20.dp))
                Column(modifier = Modifier.padding(end = 24.dp)) {
                    TitleAndEditTextField(
                        title = "Category name :",
                        placeHolder = "Category",
                        textFieldColour = Color(0xff1d1d1d), keyboardType = KeyboardType.Text
                    ) {
                        val value = it
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Create new category", fontSize = 16.sp, color = Color(0xdeffffff))
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(45.dp),
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0x35ffffff)),

                    ) {
                    Text(
                        text = "Choose icon from library",
                        color = Color(0xdeffffff),
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(28.dp))
                Text(text = "Category color :", color = Color(0xdeffffff), fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))
                SetColourPicker()
                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 24.dp)
                ) {
                    OutlinedButton(
                        onClick = { setNewCatgoryShowDialog(false) },
                        modifier = Modifier
                            .height(48.dp)
                            .weight(1f),

                        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
                        border = BorderStroke(width = 0.dp, color = Color.Transparent)

                    ) {
                        Text(text = "Cancel", color = Color(0xff8687e7), fontSize = 14.sp)
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .height(48.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff8687e7))
                    ) {
                        Text(text = "Create Category", color = Color(0xffffffff), fontSize = 14.sp)
                    }
                }
            }
        }
    }

}

@Composable
fun SetColourPicker() {
    val colourItems = mutableListOf<Color>()
    colourItems.add(Color(0xffc9cc41))
    colourItems.add(Color(0xff66cc41))
    colourItems.add(Color(0xff41cca7))
    colourItems.add(Color(0xff4181cc))
    colourItems.add(Color(0xff41a2cc))
    colourItems.add(Color(0xffcc8441))
    colourItems.add(Color(0xff9741cc))
    colourItems.add(Color(0xffcc4173))
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(colourItems) { colourItem ->
            RowColourItem(color = colourItem)
        }
    }
}

@Composable
fun RowColourItem(color: Color) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(color = color, shape = CircleShape)
    ) {
    }
    Spacer(modifier = Modifier.width(12.dp))
}

