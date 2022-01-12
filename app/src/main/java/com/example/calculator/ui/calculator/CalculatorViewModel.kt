package com.example.calculator.ui.calculator

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    private var isNegative = false
    private var hasDecimal = false
    private val storedValue: Double? = null
    private var isInit = false

    val displayContents: MutableLiveData<String> by lazy {
        MutableLiveData<String>("0")
    }

    fun buttonListener(char: String): View.OnClickListener {
        if (char in (1..9).map { digit -> digit.toString() }) {
            return View.OnClickListener { addDigit(char) }
        } else if (char == ".") {
            return View.OnClickListener { addDecimal() }
        } else if (char == "0") {
            return View.OnClickListener { addZero() }
        } else if (char == "C") {
            return View.OnClickListener { clear() }
        } else if (char == "+-") {
            return View.OnClickListener { negate() }
        } else {
            return View.OnClickListener {  }
        }
    }

    private fun addDigit(digit: String) {
        if (!isInit) {
            isInit = true
            isNegative = false
            displayContents.value = ""
        }
        displayContents.value += digit
    }

    private fun addZero() {
        Log.v("init", isInit.toString())
        if (isInit) {
            addDigit("0")
        }
    }

    private fun addDecimal() {
        if (!hasDecimal) {
            isInit = true
            displayContents.value += "."
            hasDecimal = true
        }
    }

    private fun clear() {
        isInit = false
        hasDecimal = false
        isNegative = false
        displayContents.value = "0"
    }

    private fun negate() {
        if (!isNegative) {
            displayContents.value = "-" + displayContents.value
            isNegative = true
        } else {
            displayContents.value = displayContents.value!!.substring(1, displayContents.value!!.length)
            isNegative = false
        }
    }
}