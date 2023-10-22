package com.example.calculator.domain.use_case

import com.example.calculator.domain.model.CalculatorData
import javax.inject.Inject

class CalculatorValidation
@Inject constructor() {
    operator fun invoke(calculatorData: CalculatorData): ValidationResult {

        if (calculatorData.height.isEmpty() || calculatorData.weight.isEmpty())
            return ValidationResult.Error(empty_field)

        return ValidationResult.Success
    }

    companion object {
        const val empty_field = "Field is empty"
    }
}
