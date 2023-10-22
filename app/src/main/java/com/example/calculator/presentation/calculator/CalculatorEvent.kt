package com.example.calculator.presentation.calculator

sealed class CalculatorEvent {
    data class EnteredWeight(val value: String) : CalculatorEvent()
    data class EnteredHeight(val value: String) : CalculatorEvent()
    data class EnteredAge(val value: String) : CalculatorEvent()
    data class ChangeGender(val gender : String) : CalculatorEvent()
    object Calculate : CalculatorEvent()
}