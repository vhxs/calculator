package com.example.calculator.ui.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    private val isNegative = false
    private val hasDecimal = false
    private val isInit = true

    val displayContents: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }

    fun appendToString(char: String) {
        displayContents.value += char
    }
}