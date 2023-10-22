package com.example.calculator.domain.use_case

import com.example.calculator.domain.model.CalculatorData
import javax.inject.Inject
import kotlin.jvm.Throws
import kotlin.math.pow

class CalculateBMI
@Inject constructor(
    private val calculatorValidation: CalculatorValidation,
) {
    @Throws(CalculateException::class)
    operator fun invoke(calculatorData: CalculatorData): Double {
        when (val validationResult = calculatorValidation(calculatorData)) {

            is ValidationResult.Success -> {
                return calculatorData.weight.toDouble() / (calculatorData.height.toDouble().pow(2))
            }
            is ValidationResult.Error ->
                throw CalculateException(validationResult.message)
        }
    }
}

class CalculateException(message: String) : Exception(message)
