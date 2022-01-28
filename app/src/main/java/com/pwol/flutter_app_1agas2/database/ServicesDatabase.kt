package com.pwol.flutter_app_1agas2.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Service::class], version = 1)
abstract class ServicesDatabase : RoomDatabase() {
    abstract fun serviceDao(): ServiceDao




    companion object {
         const val DATABASE_NAME = "1a_database"
        @Volatile
        private var INSTANCE: ServicesDatabase? = null

        fun getInstance(context: Application): ServicesDatabase? {
            INSTANCE ?: synchronized(this) {
                val MIGRATION_1_2 = object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        //database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                        //        "PRIMARY KEY(`id`))")
                    }
                }

                val MIGRATION_2_3 = object : Migration(2, 3) {
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
                    ServicesDatabase::class.java,
                    DATABASE_NAME
                ).build()


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