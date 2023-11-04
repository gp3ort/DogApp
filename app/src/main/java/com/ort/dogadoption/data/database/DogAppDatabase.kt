package com.ort.dogadoption.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ort.dogadoption.models.Pets


@Database(entities = [Pets::class], version = 2, exportSchema = false)
abstract class DogAppDatabase : RoomDatabase() {

    abstract fun petDAO(): PetsDAO

    companion object {

        var INSTANCE: DogAppDatabase? = null

        fun getAppDataBase(context: Context): DogAppDatabase? {
            if (INSTANCE == null) {
                synchronized(DogAppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DogAppDatabase::class.java,
                        "dogAppDB"
                    ).addMigrations().allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}