package com.pwol.flutter_app_1agas2.database.faqs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Faq.TABLE_NAME)
data class Faq(
    //@ColumnInfo(name = "phone_number") @NotNull val phoneNumber: String,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "excerpt") val excerpt: String? = null,
    @ColumnInfo(name = "body") val body: String? = null,
    @ColumnInfo(name = "category") val category: String? = null,
) {
    companion object {
        const val TABLE_NAME = "Faqs"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

}