package com.pwol.flutter_app_1agas2.database.services

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Service.TABLE_NAME)
data class Service(
    //@ColumnInfo(name = "phone_number") @NotNull val phoneNumber: String,
    @ColumnInfo(name = "contract") val contract: String? = null,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "lastname") val lastname: String? = null,
    @ColumnInfo(name = "direction") val direction: String? = null,
    @ColumnInfo(name = "type") val type: String? = null,
    @ColumnInfo(name = "state") val state: String? = null,
    @ColumnInfo(name = "closed" , defaultValue = "0")  val closed: Boolean? = false,
    @ColumnInfo(name = "timeMonoxide") val timeMonoxide: Int? = 0,
    @ColumnInfo(name = "imagesMonoxide") val imagesMonoxide: String? = null,
    @ColumnInfo(name = "imagesMonoxideResult") val imagesMonoxideResult: String? = null,
    @ColumnInfo(name = "imagesMonoxide0") val imagesMonoxide0: String? = null,
    @ColumnInfo(name = "timePresure") val timePresure: Int? = 0,
    @ColumnInfo(name = "imagesPresure") val imagesPresure: String? = null,
    @ColumnInfo(name = "imagesPresureResult") val imagesPresureResult: String? = null,
    @ColumnInfo(name = "imagesInstall") val imagesInstall: String? = null,
    @ColumnInfo(name = "imagesFan") val imagesFan: String? = null,
    @ColumnInfo(name = "imagesDefects") val imagesDefects: String? = null,
    @ColumnInfo(name = "imagesValves") val imagesValves: String? = null,
    @ColumnInfo(name = "imagesComplements") val imagesComplements: String? = null,
    @ColumnInfo(name = "imagesArtifacts") val imagesArtifacts: String? = null,
    @ColumnInfo(name = "imagesStikers") val imagesStikers: String? = null,
    @ColumnInfo(name = "imagesMaintenance") val imagesMaintenance: String? = null,
    @ColumnInfo(name = "imagesCorrosive") val imagesCorrosive: String? = null,
    @ColumnInfo(name = "imagesGrounding") val imagesGrounding: String? = null,
    @ColumnInfo(name = "imagesMeshes") val imagesMeshes: String? = null,
    @ColumnInfo(name = "imagesRevoco") val imagesRevoco: String? = null,
    @ColumnInfo(name = "recordNumber") val recordNumber: String? = null,
    @ColumnInfo(name = "notes") val notes: String? = null,
    @ColumnInfo(name = "user_id") val user_id: Int? = null,
    @ColumnInfo(name = "operative_group") val operative_group: String? = null,
    @ColumnInfo(name = "uploaded") val uploaded: Boolean? = false,
    @ColumnInfo(name = "signature") val signature: String? = null,
    @ColumnInfo(name = "signature_document") val signature_document: String? = null,
    @ColumnInfo(name = "signature_name") val signature_name: String? = null,
    @ColumnInfo(name = "end_status") val end_status: String? = null,
    @ColumnInfo(name = "booking") val booking: String? = null,
    @ColumnInfo(name = "deadline") val deadline: String? = null,
    @ColumnInfo(name = "departament") val departament: String? = null,
    @ColumnInfo(name = "municipality") val municipality: String? = null,
    @ColumnInfo(name = "field1") val field1: String? = null,
    @ColumnInfo(name = "field2") val field2: String? = null,
    @ColumnInfo(name = "field3") val field3: String? = null,
    @ColumnInfo(name = "field4") val field4: String? = null,
    @ColumnInfo(name = "field5") val field5: String? = null,
    @ColumnInfo(name = "field6") val field6: String? = null,
    @ColumnInfo(name = "field7") val field7: String? = null,
    @ColumnInfo(name = "field8") val field8: String? = null,
    @ColumnInfo(name = "field9") val field9: String? = null,
    @ColumnInfo(name = "field10") val field10: String? = null,

) {
    companion object {
        const val TABLE_NAME = "Services"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

}