package com.pwol.flutter_app_1agas2.database.departaments

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class MunicipalitiesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MunicipalitiesRepository(application)
    val municipalities = repository.getMunicipalities()
}