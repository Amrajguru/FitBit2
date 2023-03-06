package com.example.fitbit2

import android.app.Application

class InputApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}