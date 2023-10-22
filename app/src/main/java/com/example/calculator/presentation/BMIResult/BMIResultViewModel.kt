package com.example.calculator.presentation.BMIResult

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.model.SelectBmiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BMIResultViewModel
@Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(
        SelectBmiState(
            savedStateHandle.get<Boolean>("isManSelected") ?: true,
            savedStateHandle.get<Float>("bmiResult")?.toDouble() ?: 0.0
        )
    )
    val state: State<SelectBmiState> = _state
}