package com.aegis.fundplus

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FundPlusApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Background Workers or Security Sandbox here
    }
}
