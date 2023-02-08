package com.example.uptodo.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.uptodo.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val fonts = FontFamily(
    Font(R.font.lato_black),
    Font(R.font.lato_black_italic),
    Font(R.font.lato_bold),
    Font(R.font.lato_bold_italic),
    Font(R.font.lato_italic),
    Font(R.font.lato_light),
    Font(R.font.lato_light_italic),
    Font(R.font.lato_regular),
    Font(R.font.lato_thin),
    Font(R.font.lato_thin_italic),
)