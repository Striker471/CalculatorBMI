package com.example.calculator.utill

sealed class Screen(val route: String) {
    object CalculatorScreen : Screen("calculator_screen")
    object BMIResultScreen : Screen("bmi_result_screen")
}
