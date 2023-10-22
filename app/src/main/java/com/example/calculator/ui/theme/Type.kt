package com.example.calculator.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.betareadingapp.R

val CustomFont = FontFamily(Font(R.font.inter_semi_bold))

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = CustomFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp
    ),
    h2 = TextStyle(
        fontFamily = CustomFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp
    ),
    h1 = TextStyle(
        fontFamily = CustomFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),

     */
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp
    )

)