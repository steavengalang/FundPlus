package com.aegis.fundplus.feature.input

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class InputUiState(
    val currentExpression: String = "0",
    val calculatedAmount: Double = 0.0,
    val selectedCategoryTitle: String = "Pilih Kategori"
)

@HiltViewModel
class InputViewModel @Inject constructor(
    // Repository akan di-inject nanti via Domain layer The Apex Blueprint
) : ViewModel() {

    private val _uiState = MutableStateFlow(InputUiState())
    val uiState: StateFlow<InputUiState> = _uiState.asStateFlow()

    fun onNumberEntered(number: String) {
        _uiState.update { state ->
            val expr = if (state.currentExpression == "0" && number != ".") number else state.currentExpression + number
            state.copy(currentExpression = expr)
        }
        evaluateExpression()
    }

    fun onOperatorEntered(operator: String) {
        _uiState.update { state ->
            state.copy(currentExpression = state.currentExpression + operator)
        }
    }

    fun onBackspace() {
        _uiState.update { state ->
            val expr = if (state.currentExpression.length <= 1) "0" else state.currentExpression.dropLast(1)
            state.copy(currentExpression = expr)
        }
        evaluateExpression()
    }

    private fun evaluateExpression() {
        // Simulasi evaluator matematika yang super cepat (Real engine can use MathParser)
        try {
            val expression = _uiState.value.currentExpression.replace("×", "*").replace("÷", "/")
            // Placeholder: Untuk MVI sungguhan, gunakan evaluator matematika pihak ketiga (exp4j / parser Kotlin)
            // Disini diasumsikan hanya angka tunggal dahulu, kalkulator disempurnakan di Phase AI
            val amount = expression.toDoubleOrNull() ?: 0.0
            
            _uiState.update { it.copy(calculatedAmount = amount) }
        } catch (e: Exception) {
            // Ignore parse errors safely
        }
    }

    fun saveTransaction() {
        // Kueri eksekusi Room Database yang akan dikirim melalui arsitektur MVI
        val finalAmount = _uiState.value.calculatedAmount
        if (finalAmount > 0) {
            // ... Emit Action to Repository
        }
    }
}
