package com.pwol.flutter_app_1agas2.database.departaments

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DepartamentDao {
    @Query("SELECT * FROM " + Departament.TABLE_NAME + " ORDER BY id")
    fun getOrderedDepartaments(): LiveData<List<Departament>>

    @Query("SELECT * FROM " + Departament.TABLE_NAME + " WHERE departament = :departament LIMIT 1")
    fun findDepartamentByName(departament: String): LiveData<Departament>


}