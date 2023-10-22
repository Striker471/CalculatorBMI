package com.example.calculator.presentation.calculator

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.domain.model.CalculatorData
import com.example.calculator.domain.model.CalculatorState
import com.example.calculator.domain.use_case.CalculateBMI
import com.example.calculator.domain.use_case.CalculateException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel
@Inject constructor(
    private val calculateBMI: CalculateBMI,
) : ViewModel() {


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _state = mutableStateOf(CalculatorState())
    val state: State<CalculatorState> = _state


    fun onEvent(event: CalculatorEvent) {
        when (event) {
            is CalculatorEvent.EnteredAge ->
                _state.value = state.value.copy(
                    age = event.value
                )

            is CalculatorEvent.EnteredHeight ->
                _state.value = state.value.copy(
                    height = event.value
                )

            is CalculatorEvent.EnteredWeight ->
                _state.value = state.value.copy(
                    weight = event.value
                )

            is CalculatorEvent.ChangeGender ->
                if ((event.gender == "male" && !state.value.isManSelected) ||
                    (event.gender == "female" && state.value.isManSelected)
                ) {
                    _state.value = state.value.copy(
                        isManSelected = !state.value.isManSelected
                    )
                }

            is CalculatorEvent.Calculate -> {
                viewModelScope.launch {
                    try {

                        val result = calculateBMI(
                            CalculatorData(
                                _state.value.weight,
                                _state.value.height
                            )
                        )
                        _eventFlow.emit(UiEvent.SubmitBMI(result))
                    } catch (e: CalculateException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't count BMI"
                            )
                        )
                    }
                }

            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data class SubmitBMI(val bmiResult: Double) : UiEvent()
    }
}