package com.pwol.flutter_app_1agas2.database.services

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pwol.flutter_app_1agas2.database.faqs.Faq

@Dao
interface FaqDao {
    @Insert
    fun insert(faq: Faq)

    @Update
    fun update(vararg faq: Faq)

    @Delete
    fun delete(vararg faq: Faq)

    @Query("SELECT * FROM " + Faq.TABLE_NAME + " ORDER BY id")
    fun getOrderedFaqs(): LiveData<List<Faq>>
}