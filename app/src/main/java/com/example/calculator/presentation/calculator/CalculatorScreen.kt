@file:OptIn(ExperimentalMaterialApi::class)

package com.example.calculator.presentation.calculator
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.betareadingapp.R
import com.example.calculator.presentation.components.TopBarTextWithImage
import com.example.calculator.utill.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CalculatorScreen(
    navController: NavController,
    viewModel: CalculatorViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val weight = viewModel.state.value.weight
    val height = viewModel.state.value.height
    val age = viewModel.state.value.age


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is CalculatorViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is CalculatorViewModel.UiEvent.SubmitBMI -> {

                    navController.navigate(
                        Screen.BMIResultScreen.route +
                                "?bmiResult=${event.bmiResult}&isManSelected=${viewModel.state.value.isManSelected}"
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopBarTextWithImage(
                stringResource(R.string.calculator_topbar)
            )
        },
        scaffoldState = scaffoldState

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(bottom = paddingValues.calculateBottomPadding(), top = 55.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                TextFieldWithText(
                    textvalue = stringResource(R.string.weight),
                    value = weight,
                    onValueChange = { viewModel.onEvent(CalculatorEvent.EnteredWeight(it)) },
                    regex = "\\d*[.]?\\d*".toRegex()
                )
                TextFieldWithText(
                    textvalue = stringResource(R.string.height),
                    value = height,
                    onValueChange = { viewModel.onEvent(CalculatorEvent.EnteredHeight(it)) },
                    regex = "\\d*[.]?\\d*".toRegex()
                )
                TextFieldWithText(
                    textvalue = stringResource(R.string.age),
                    value = age,
                    onValueChange = { viewModel.onEvent(CalculatorEvent.EnteredAge(it)) },
                    regex = "[1-9]\\d*|".toRegex(),
                )
                Spacer(modifier = Modifier.padding(bottom = 35.dp))
                Text(
                    text = stringResource(R.string.sex),
                    style = MaterialTheme.typography.body1
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 35.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = stringResource(R.string.male),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.clickable {
                        viewModel.onEvent(CalculatorEvent.ChangeGender("male"))
                    },
                    color = if (viewModel.state.value.isManSelected) MaterialTheme.colors.onBackground else colorResource(
                        R.color.grey
                    )

                )
                Text(
                    text = stringResource(R.string.female),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.clickable {
                        viewModel.onEvent(CalculatorEvent.ChangeGender("female"))
                    },
                    color = if (!viewModel.state.value.isManSelected) MaterialTheme.colors.onBackground else colorResource(
                        R.color.grey
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.onEvent(CalculatorEvent.Calculate)
                },
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                ),
                modifier = Modifier
                    .padding(bottom = 45.dp)
                    .fillMaxWidth(0.29f)
                    .height(41.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.button_orange)),
                content = {
                    Text(
                        text = stringResource(R.string.submit),
                    )
                },
                shape = MaterialTheme.shapes.medium
            )
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    regex: Regex,

    ) {
    val interactionSource = remember { MutableInteractionSource() }
    val enabled = true
    val singleLine = true

    val colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colors.surface,
        unfocusedBorderColor = MaterialTheme.colors.surface,
        backgroundColor = MaterialTheme.colors.surface,
        textColor = MaterialTheme.colors.onSurface,
        placeholderColor = MaterialTheme.colors.onSurface
    )

    BasicTextField(
        value = value,
        onValueChange = {
            if (regex.matches(it)) {
                onValueChange(it)
            }
        },
        modifier = Modifier
            .fillMaxWidth(0.62f)
            .height(32.dp)
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colors.surface),
        interactionSource = interactionSource,
        enabled = enabled,
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
    ) {
        TextFieldDefaults.OutlinedTextFieldDecorationBox(
            value = value,
            visualTransformation = VisualTransformation.None,
            innerTextField = it,
            singleLine = singleLine,
            enabled = enabled,
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                start = 10.dp, end = 0.dp, top = 0.dp
            ),
            border = {
                TextFieldDefaults.BorderBox(
                    enabled = enabled,
                    isError = false,
                    colors = colors,
                    interactionSource = interactionSource,
                    unfocusedBorderThickness = 2.dp,
                    focusedBorderThickness = 4.dp
                )
            },
            colors = colors
        )
    }
}

@Composable
fun TextFieldWithText(
    textvalue: String,
    value: String,
    onValueChange: (String) -> Unit,
    regex: Regex,
) {
    Text(
        text = textvalue,
        style = MaterialTheme.typography.body1,
        modifier = Modifier
//            .padding(bottom = 5.dp),
    )

    CustomTextField(value, onValueChange, regex)
    Spacer(modifier = Modifier.height(16.dp))

}