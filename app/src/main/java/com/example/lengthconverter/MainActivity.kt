package com.example.lengthconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lengthconverter.ui.theme.LengthConverterTheme
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LengthConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LengthConverterScreen()
                }
            }
        }
    }
}

fun formatResult(value: Double?): String {
    if (value == null) return ""
    if (value == 0.0) return "0"

    val absVal = kotlin.math.abs(value)
    if (absVal < 0.001 || absVal >= 1000000.0) {
        return String.format(Locale.US, "%.3e", value).replace("e", " × 10^")
    }

    val symbols = DecimalFormatSymbols(Locale.getDefault()).apply {
        decimalSeparator = ','
        groupingSeparator = ' '
    }
    val formatter = DecimalFormat("#,##0.####", symbols)
    return formatter.format(value)
}

@Composable
fun LengthConverterScreen() {
    var inputMeters by rememberSaveable { mutableStateOf("") }

    val normalizedInput = inputMeters.replace(',', '.')
    val metersValue = normalizedInput.toDoubleOrNull()
    val isInvalidInput = inputMeters.isNotEmpty() && metersValue == null

    val centimeters = metersValue?.let { it * 100.0 }
    val kilometers = metersValue?.let { it / 1000.0 }
    val miles = metersValue?.let { it / 1609.344 }
    val lightYears = metersValue?.let { it / 9460730472580800.0 }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Length Converter",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = inputMeters,
            onValueChange = { inputMeters = it },
            label = { Text("Długość w metrach") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = isInvalidInput,
            modifier = Modifier.fillMaxWidth()
        )

        if (isInvalidInput) {
            Text(
                text = "Wprowadź poprawną liczbę!",
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Centymetry: ${formatResult(centimeters)}", fontSize = 18.sp)
        Text(text = "Kilometry: ${formatResult(kilometers)}", fontSize = 18.sp)
        Text(text = "Mile: ${formatResult(miles)}", fontSize = 18.sp)
        Text(text = "Lata świetlne: ${formatResult(lightYears)}", fontSize = 18.sp)
    }
}