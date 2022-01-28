package com.pwol.flutter_app_1agas2.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ServiceDao {
    @Insert
    fun insert(service: Service)

    @Update
    fun update(vararg service: Service)

    @Delete
    fun delete(vararg service: Service)

    @Query("SELECT * FROM " + Service.TABLE_NAME + " ORDER BY id")
    fun getOrderedAgenda(): LiveData<List<Service>>
}