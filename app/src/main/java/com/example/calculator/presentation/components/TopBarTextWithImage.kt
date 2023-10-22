package com.example.calculator.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.betareadingapp.R
@Composable
fun TopBarTextWithImage(title: String) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        contentColor = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .height(70.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                title,
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Small Logo",
                modifier = Modifier
                    .size(60.dp)
                    .offset(x = (-30).dp, y = 5.dp)
            )
        }
    }
}