package com.pwol.flutter_app_1agas2.database.departaments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pwol.flutter_app_1agas2.database.GlobalDatabase
import com.pwol.flutter_app_1agas2.database.municipalities.Municipality

class MunicipalitiesRepository(application: Application) {
    private val municipalityDao: MunicipalityDao? = GlobalDatabase.getInstance(application)?.municypalityDao()

    fun getMunicipalities(): LiveData<List<Municipality>> {
        return municipalityDao?.getOrderedMunicipalities() ?: MutableLiveData<List<Municipality>>()
    }

    fun getMunicipality(id: Int): LiveData<Municipality> {
        return municipalityDao?.getMunicipality(id) ?: MutableLiveData<Municipality>()
    }

    fun getMunicipalitiesByDepartamentId(id_departamento: Long): LiveData<List<Municipality>> {
        return municipalityDao?.getMunicipalitiesByDepartamentId(id_departamento) ?: MutableLiveData<List<Municipality>>()
    }

}