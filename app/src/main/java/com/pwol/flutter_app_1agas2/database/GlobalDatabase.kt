package com.pwol.flutter_app_1agas2.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pwol.flutter_app_1agas2.database.departaments.Departament
import com.pwol.flutter_app_1agas2.database.departaments.DepartamentDao
import com.pwol.flutter_app_1agas2.database.faqs.Faq
import com.pwol.flutter_app_1agas2.database.services.Service
import com.pwol.flutter_app_1agas2.database.services.FaqDao
import com.pwol.flutter_app_1agas2.database.services.ServiceDao


@Database(entities = [Service::class, Faq::class, Departament::class], version = 2)
abstract class GlobalDatabase : RoomDatabase() {
    abstract fun serviceDao(): ServiceDao
    abstract fun departamentDao(): DepartamentDao
    abstract fun faqDao(): FaqDao

    companion object {
         const val DATABASE_NAME = "1a_database"
        @Volatile
        private var INSTANCE: GlobalDatabase? = null

        fun getInstance(context: Application): GlobalDatabase? {
            INSTANCE ?: synchronized(this) {
                val MIGRATION_1_2 = object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.execSQL(
                            "INSERT INTO ${Departament.TABLE_NAME} (id_departamento, departament)  " +
                                    " VALUES " +
                                    "(5,'ANTIOQUIA')," +
                                    "(8,'ATLÁNTICO')," +
                                    "(11,'BOGOTÁ, D.C.')," +
                                    "(13,'BOLÍVAR')," +
                                    "(15,'BOYACÁ')," +
                                    "(17,'CALDAS')," +
                                    "(18,'CAQUETÁ')," +
                                    "(19,'CAUCA')," +
                                    "(20,'CESAR')," +
                                    "(23,'CÓRDOBA')," +
                                    "(25,'CUNDINAMARCA')," +
                                    "(27,'CHOCÓ')," +
                                    "(41,'HUILA')," +
                                    "(44,'LA GUAJIRA')," +
                                    "(47,'MAGDALENA')," +
                                    "(50,'META')," +
                                    "(52,'NARIÑO')," +
                                    "(54,'NORTE DE SANTANDER')," +
                                    "(63,'QUINDIO')," +
                                    "(66,'RISARALDA')," +
                                    "(68,'SANTANDER')," +
                                    "(70,'SUCRE')," +
                                    "(73,'TOLIMA')," +
                                    "(76,'VALLE DEL CAUCA')," +
                                    "(81,'ARAUCA')," +
                                    "(85,'CASANARE')," +
                                    "(86,'PUTUMAYO')," +
                                    "(88,'ARCHIPIÉLAGO DE SAN ANDRÉS, PROVIDENCIA Y SANTA CATALINA')," +
                                    "(91,'AMAZONAS')," +
                                    "(94,'GUAINÍA')," +
                                    "(95,'GUAVIARE')," +
                                    "(97,'VAUPÉS')," +
                                    "(99,'VICHADA');"
                        )
                    }
                }
                val MIGRATION_1_3 = object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        //database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                        //        "PRIMARY KEY(`id`))")

                    }
                }

                val MIGRATION_2_4 = object : Migration(2, 3) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        //database.execSQL("ALTER TABLE contact ADD COLUMN demo INTEGER")
                        //database.execSQL("""
                        //    INSERT INTO new_Song (id, name, tag)
                        //    SELECT id, name, tag FROM Song
                        //    """.trimIndent())
                        //database.execSQL("DROP TABLE Song")
                        //database.execSQL("ALTER TABLE new_Song RENAME TO Song")
                        //

                    }
                }


                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    GlobalDatabase::class.java,
                    DATABASE_NAME
                ).addMigrations(MIGRATION_1_2).build()


                //INSTANCE = Room.databaseBuilder(
                //    context.applicationContext,
                //    ServicesDatabase::class.java,
                //    DATABASE_NAME
                //).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()
            }
            return INSTANCE
        }
    }

}