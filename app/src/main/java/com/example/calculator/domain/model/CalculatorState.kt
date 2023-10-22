package com.example.calculator.domain.model

data class CalculatorState(
    val weight: String = "",
    val height: String = "",
    val age: String = "",
    val isManSelected: Boolean = true
)
