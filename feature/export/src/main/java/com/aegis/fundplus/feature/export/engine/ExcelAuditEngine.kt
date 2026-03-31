package com.aegis.fundplus.feature.export.engine

import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.OutputStream
import javax.inject.Inject

/**
 * Mesin Akuntan Publik. Mencetak Excel .xlsx asli dengan formatting kelas satu.
 */
class ExcelAuditEngine @Inject constructor() {

    fun generateAuditReport(outputStream: OutputStream) {
        val workbook = XSSFWorkbook()

        try {
            // ============================================
            // DRAFT SHEET 1: REKAP EKSEKUTIF (DASHBOARD)
            // ============================================
            val summarySheet = workbook.createSheet("Rekap Eksekutif")
            val summaryHeaderRow = summarySheet.createRow(0)
            
            // Format Tajam untuk Header
            val headerStyle = workbook.createCellStyle().apply {
                fillForegroundColor = IndexedColors.GREY_80_PERCENT.index
                fillPattern = FillPatternType.SOLID_FOREGROUND
                borderBottom = BorderStyle.DOUBLE
            }
            val fontHeader = workbook.createFont().apply {
                color = IndexedColors.WHITE.index
                bold = true
            }
            headerStyle.setFont(fontHeader)

            val sTitleCell = summaryHeaderRow.createCell(0)
            sTitleCell.setCellValue("Fund+ Executive Financial Audit")
            sTitleCell.cellStyle = headerStyle

            // Formula Dinamis Excel =SUMIFS (Bayangan AI)
            val metricRow = summarySheet.createRow(2)
            metricRow.createCell(0).setCellValue("Total Pengeluaran:")
            val formulaCell = metricRow.createCell(1)
            // Formula merujuk ke Tab Database
            formulaCell.cellFormula = "SUMIFS('Database Mutasi'!D:D, 'Database Mutasi'!E:E, \"EXPENSE\")"
            
            // ============================================
            // DRAFT SHEET 2: DATABASE MUTASI (RAW DATA)
            // ============================================
            val dataSheet = workbook.createSheet("Database Mutasi")
            val dataHeaderRow = dataSheet.createRow(0)

            val columns = arrayOf("Tanggal", "ID Transaksi", "Kategori", "Nominal", "Tipe", "Catatan")
            columns.forEachIndexed { index, colName ->
                val cell = dataHeaderRow.createCell(index)
                cell.setCellValue(colName)
                cell.cellStyle = headerStyle
                dataSheet.setColumnWidth(index, 256 * 20) // Lebar Otomatis
            }

            // --- Simulasi penyisipan data dari SQLite berformat Currency ---
            val currencyStyle = workbook.createCellStyle().apply {
                val format = workbook.createDataFormat()
                dataFormat = format.getFormat("Rp #,##0.00") // Format Native Uang
            }

            // Simulasi Baris Pertama DB
            val row1 = dataSheet.createRow(1)
            row1.createCell(0).setCellValue("2024-10-15")
            row1.createCell(1).setCellValue("TXN-A91B2")
            row1.createCell(2).setCellValue("Gaya Hidup")
            val amountCell = row1.createCell(3)
            amountCell.setCellValue(1500000.0)
            amountCell.cellStyle = currencyStyle // Angka masuk sebagai Double, bukan String!
            row1.createCell(4).setCellValue("EXPENSE")
            row1.createCell(5).setCellValue("Oculus VR Headset")

            // Auto-Filter Nyala secara Default di Excel
            dataSheet.setAutoFilter(org.apache.poi.ss.util.CellRangeAddress(0, 500, 0, 5))

            // Menyemburkan (Flush) seluruh data di atas ke Memori I/O HP melalui Stream SAF
            workbook.write(outputStream)

        } finally {
            workbook.close()
        }
    }
}
