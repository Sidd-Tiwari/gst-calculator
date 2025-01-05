package com.example.gstcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.gstcalculator.ui.theme.GSTCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GSTCalculatorTheme {
                ScaffoldView()
            }
        }
    }
}
@Composable
fun ScaffoldView() {
    val context = LocalContext.current
    var gstRate by remember { mutableStateOf(18.0) } // Use Double for GST rate
    var selectedRate by remember { mutableStateOf(18.0) }
    var amountWithoutGst by remember { mutableStateOf("") }
    var amountWithGst by remember { mutableStateOf("") }
    var gstAmount by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // GST Rate Selection
        item {
            Text(text = "Select GST Rate")
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // First row of buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf(0.25, 3.0, 5.0).forEach { rate ->
                        Button(
                            onClick = {
                                gstRate = rate
                                selectedRate = rate
                            },
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = if (selectedRate == rate) androidx.compose.ui.graphics.Color.Green else androidx.compose.ui.graphics.Color.Gray
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "$rate%")
                        }
                    }
                }
                // Second row of buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf(12.0, 18.0, 28.0).forEach { rate ->
                        Button(
                            onClick = {
                                gstRate = rate
                                selectedRate = rate
                            },
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = if (selectedRate == rate) androidx.compose.ui.graphics.Color.Green else androidx.compose.ui.graphics.Color.Gray
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "$rate%")
                        }
                    }
                }
            }
        }

        // Amount Without GST Input
        item {
            TextField(
                value = amountWithoutGst,
                onValueChange = {
                    amountWithoutGst = it
                    if (validateInput(context, it)) {
                        val amount = it.toDoubleOrNull() ?: 0.0
                        gstAmount = (amount * gstRate / 100).toString()
                        amountWithGst = (amount + gstAmount.toDouble()).toString()
                    }
                },
                label = { Text(text = "Enter Amount Without GST") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Amount With GST Input
        item {
            TextField(
                value = amountWithGst,
                onValueChange = {
                    amountWithGst = it
                    if (validateInput(context, it)) {
                        val totalAmount = it.toDoubleOrNull() ?: 0.0
                        amountWithoutGst = (totalAmount / (1 + gstRate / 100.0)).toString()
                        gstAmount = (totalAmount - amountWithoutGst.toDouble()).toString()
                    }
                },
                label = { Text(text = "Enter Amount With GST") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        // GST Amount Input
        item {
            TextField(
                value = gstAmount,
                onValueChange = {
                    gstAmount = it
                    if (validateInput(context, it)) {
                        val gstValue = it.toDoubleOrNull() ?: 0.0
                        amountWithoutGst = (gstValue / (gstRate / 100.0)).toString()
                        amountWithGst = (amountWithoutGst.toDouble() + gstValue).toString()
                    }
                },
                label = { Text(text = "Enter GST Amount") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        // GST Breakdown (IGST, CGST, SGST)
        item {
            if (gstAmount.isNotBlank()) {
                val gstValue = gstAmount.toDouble()
                Text(text = "IGST: ₹${gstValue}")
                Text(text = "CGST: ₹${gstValue / 2}")
                Text(text = "SGST: ₹${gstValue / 2}")
            }
        }

        // Clear Button
        item {
            Button(
                onClick = {
                    gstRate = 18.0
                    selectedRate = 18.0
                    amountWithGst = ""
                    amountWithoutGst = ""
                    gstAmount = ""
                    Toast.makeText(context, "Cleared", Toast.LENGTH_SHORT).show()
                },
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = androidx.compose.ui.graphics.Color.Red
                )
            ) {
                Text(text = "Clear")
            }
        }
    }
}

// Input Validation
fun validateInput(context: android.content.Context, input: String): Boolean {
    return if (input.isEmpty() || input.count { it == '.' } > 1 || input.contains(Regex("[^0-9.]"))) {
        Toast.makeText(context, "Invalid input. Please enter a valid number.", Toast.LENGTH_SHORT).show()
        false
    } else {
        true
    }
}
