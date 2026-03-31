package com.aegis.fundplus.feature.export

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aegis.fundplus.feature.export.engine.ExcelAuditEngine
import com.aegis.fundplus.feature.export.engine.PdfForensicGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExportViewModel @Inject constructor(
    private val excelEngine: ExcelAuditEngine,
    private val pdfEngine: PdfForensicGenerator
) : ViewModel() {

    // SAF (Storage Access Framework) menghindari MANAGE_EXTERNAL_STORAGE permission
    // URI ini didapat dari hasil pemanggilan: Intent(Intent.ACTION_CREATE_DOCUMENT) di layar UI Activity Compose
    fun exportToExcel(contentResolver: ContentResolver, documentUri: Uri, onSuccess: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val outputStream = contentResolver.openOutputStream(documentUri)
                outputStream?.use { stream ->
                    excelEngine.generateAuditReport(stream)
                }
            }
            onSuccess()
        }
    }

    fun exportToSecurePdf(contentResolver: ContentResolver, documentUri: Uri, onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            var hashSignature = ""
            withContext(Dispatchers.IO) {
                val outputStream = contentResolver.openOutputStream(documentUri)
                outputStream?.use { stream ->
                    hashSignature = pdfEngine.generateSignedPdf(stream)
                }
            }
            onSuccess(hashSignature) // Mengembalikan kode hash ke Toast UI
        }
    }
}
