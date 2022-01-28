package com.pwol.flutter_app_1agas2.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class ServicesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ServicesRepository(application)
    val services = repository.getServices()

    fun saveContact(service: Service) {
        repository.insert(service)
    }
}