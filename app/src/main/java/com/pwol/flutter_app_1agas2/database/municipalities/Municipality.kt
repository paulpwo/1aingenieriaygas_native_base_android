package com.pwol.flutter_app_1agas2.database.municipalities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Municipality.TABLE_NAME)
data class Municipality(
    @ColumnInfo(name = "id_municipio") val id_municipio: Long? = null,
    @ColumnInfo(name = "municipio") val municipio: String? = null,
    @ColumnInfo(name = "estado") val estado: Int? = null,
    @ColumnInfo(name = "departamento_id") val departamento_id: Int? = null,
) {
    companion object {
        const val TABLE_NAME = "Municipalities"
    }
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}