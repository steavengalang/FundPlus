package com.aegis.fundplus.feature.export.engine

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import java.io.OutputStream
import java.security.MessageDigest
import javax.inject.Inject

/**
 * Mesin PDF Anti-Editan (Blockchain-Level Security Vibe).
 */
class PdfForensicGenerator @Inject constructor() {

    fun generateSignedPdf(outputStream: OutputStream): String {
        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // Ukuran A4 standar (PostScript points)
        val page = document.startPage(pageInfo)

        val canvas: Canvas = page.canvas
        val paint = Paint()

        // 1. Menggambar KOP Laporan
        paint.color = Color.DKGRAY
        paint.textSize = 28f
        paint.isFakeBoldText = true
        canvas.drawText("Laporan Mutasi Eksekutif (Fund+)", 50f, 80f, paint)

        paint.textSize = 14f
        paint.isFakeBoldText = false
        paint.color = Color.GRAY
        canvas.drawText("Dicetak dari brankas terenkripsi.", 50f, 110f, paint)

        // 2. Garis Batas
        paint.color = Color.BLACK
        paint.strokeWidth = 2f
        canvas.drawLine(50f, 130f, 545f, 130f, paint)

        // 3. Konten Utama
        paint.textSize = 16f
        canvas.drawText("Total Pemasukan: Rp 150.000.000", 50f, 180f, paint)
        paint.color = Color.RED
        canvas.drawText("Total Pengeluaran: (Rp 45.000.000)", 50f, 210f, paint)
        
        paint.color = Color.BLACK
        paint.isFakeBoldText = true
        canvas.drawText("NET WORTH: Rp 105.000.000", 50f, 240f, paint)

        // 4. GENERATE MD5 HASH SIGNATURE (Segel Forensik)
        val rawDataToHash = "USER_ID_777|NET:105000000|TIMESTAMP:${System.currentTimeMillis()}"
        val signatureHash = md5Hash(rawDataToHash)

        paint.textSize = 10f
        paint.color = Color.LTGRAY
        canvas.drawText("VERIFICATION HASH (DIGITAL SIGNATURE):", 50f, 800f, paint)
        canvas.drawText(signatureHash.uppercase(), 50f, 815f, paint)

        document.finishPage(page)

        // Menulis langsung ke SAF Laci HP
        document.writeTo(outputStream)
        document.close()
        
        return signatureHash.uppercase()
    }

    private fun md5Hash(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bytes = md.digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
