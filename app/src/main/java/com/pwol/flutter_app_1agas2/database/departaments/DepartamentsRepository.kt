package com.pwol.flutter_app_1agas2.database.departaments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pwol.flutter_app_1agas2.database.GlobalDatabase

class DepartamentsRepository(application: Application) {
    private val departamentDao: DepartamentDao? = GlobalDatabase.getInstance(application)?.departamentDao()

    fun getDepartaments(): LiveData<List<Departament>> {
        return departamentDao?.getOrderedDepartaments() ?: MutableLiveData<List<Departament>>()
    }

    fun findDepartamentByName(departament: String): LiveData<Departament> {
        return departamentDao?.findDepartamentByName(departament) ?: MutableLiveData<Departament>()
    }

}