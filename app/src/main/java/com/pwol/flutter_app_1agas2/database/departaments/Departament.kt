package com.pwol.flutter_app_1agas2.database.departaments

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Departament.TABLE_NAME)
data class Departament(
    //@ColumnInfo(name = "phone_number") @NotNull val phoneNumber: String,
    @ColumnInfo(name = "id_departamento") val id_departamento: Int? = null,
    @ColumnInfo(name = "departament") val departament: String? = null,
) {
    companion object {
        const val TABLE_NAME = "Departaments"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

}