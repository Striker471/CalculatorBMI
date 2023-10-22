@file:OptIn(ExperimentalMaterialApi::class)

package com.example.calculator.presentation.BMIResult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.betareadingapp.R
import com.example.calculator.domain.model.CalculatorData
import com.example.calculator.presentation.components.TopBarTextWithImage

@Composable
fun BMIResultScreen(
    navController: NavController,
    viewModel: BMIResultViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            TopBarTextWithImage(stringResource(R.string.calculator_topbar))
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(bottom = paddingValues.calculateBottomPadding())
                .fillMaxSize(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                IconButton(
                    onClick = { navController.navigateUp() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
                Text(
                    stringResource(R.string.return_arrow),
                    modifier = Modifier.offset(x = (-5).dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.padding(top = 45.dp))
                ResultText(
                    stringResource(R.string.result),
                    String.format("%.3f", viewModel.state.value.bmiResult) + " kg/m2"
                )
                Spacer(modifier = Modifier.height(35.dp))
                Image(
                    painter = painterResource(
                        selectImage(
                            viewModel.state.value.bmiResult,
                            viewModel.state.value.isManSelected
                        )
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(370.dp)
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = selectTitle(viewModel.state.value.bmiResult),
                    style = MaterialTheme.typography.h2
                )
            }
        }
    }
}

fun selectTitle(bmi: Double): String {
    return when {
        bmi < 18.5 -> "Underweight"
        bmi in 18.5..24.9 -> "Normal"
        bmi in 25.0..29.9 -> "Overweight"
        else -> "Obese"
    }
}


fun selectImage(bmi: Double, isManSelected: Boolean): Int {
    return when {
        isManSelected -> {
            when {
                bmi < 18.5 -> R.drawable.underweight_male
                bmi in 18.5..24.9 -> R.drawable.normal_male
                bmi in 25.0..29.9 -> R.drawable.overweight_male
                else -> R.drawable.obese_male
            }
        }

        else -> {
            when {
                bmi < 18.5 -> R.drawable.underweight
                bmi in 18.5..24.9 -> R.drawable.normal
                bmi in 25.0..29.9 -> R.drawable.overweight
                else -> R.drawable.obese
            }
        }
    }
}


@Composable
fun ResultText(
    label: String,
    text: String,
) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
        )

        Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth(0.62f)
                .height(36.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.h2.copy(
                        color = colorResource(R.color.grey),
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}
