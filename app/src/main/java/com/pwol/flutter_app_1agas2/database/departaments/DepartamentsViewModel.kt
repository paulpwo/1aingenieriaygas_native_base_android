package com.pwol.flutter_app_1agas2.database.departaments

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class DepartamentsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DepartamentsRepository(application)
    val departaments = repository.getDepartaments()
}