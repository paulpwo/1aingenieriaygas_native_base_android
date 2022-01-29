package com.pwol.flutter_app_1agas2.database.departaments

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pwol.flutter_app_1agas2.database.municipalities.Municipality

@Dao
interface MunicipalityDao {
    @Query("SELECT * FROM " + Municipality.TABLE_NAME + " ORDER BY id")
    fun getOrderedMunicipalities(): LiveData<List<Municipality>>

    @Query("SELECT * FROM " + Municipality.TABLE_NAME + " WHERE id = :id")
    fun getMunicipality(id: Int): LiveData<Municipality>

    @Query("SELECT * FROM " + Municipality.TABLE_NAME + " WHERE departamento_id = :departamento_id")
    fun getMunicipalitiesByDepartamentId(departamento_id: Long): LiveData<List<Municipality>>

}