package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.core.view.children
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.ui.buttons.DecimalButton
import com.example.calculator.ui.buttons.DigitButton
import com.example.calculator.ui.buttons.OperationButton
import com.example.calculator.ui.calculator.CalculatorViewModel

class MainActivity : AppCompatActivity() {
    private val model: CalculatorViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        for (child in binding.buttonGrid.children) {
            if (child is Button) {
                val buttonLabel = child.text.toString()
                val listener = model.buttonListener(buttonLabel)
                child.setOnClickListener(listener)
            }
        }

        model.displayContents.observe(this, { displayString ->
            binding.display.text = displayString
        })

        setContentView(binding.root)
    }
}