package com.aegis.fundplus.feature.input.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputScreen() {
    val displayValue = remember { mutableStateOf("0") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F0F0F))
            .padding(top = 64.dp, bottom = 120.dp), // Beri ruang untuk Bottom Bar
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Nominal Transaksi",
            color = Color.Gray,
            fontSize = 14.sp
        )
        Text(
            text = "Rp ${displayValue.value}",
            color = Color.White,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp, bottom = 48.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        SmartNumpad(
            onNumberClick = { 
                if (displayValue.value == "0") displayValue.value = it 
                else displayValue.value += it 
            },
            onOperatorClick = { /* Nanti dipasang logika math */ },
            onBackspaceClick = { 
                if (displayValue.value.length > 1) {
                    displayValue.value = displayValue.value.dropLast(1)
                } else {
                    displayValue.value = "0"
                }
            },
            onDoneClick = { /* Nanti dipasang trigger simpan database */ }
        )
    }
}
