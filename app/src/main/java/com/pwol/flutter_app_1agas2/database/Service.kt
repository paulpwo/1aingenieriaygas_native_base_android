package com.pwol.flutter_app_1agas2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = Service.TABLE_NAME)
data class Service(
    //@ColumnInfo(name = "phone_number") @NotNull val phoneNumber: String,
    @ColumnInfo(name = "init_filed") val init_filed: String? = null
) {
    companion object {
        const val TABLE_NAME = "Services"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

}