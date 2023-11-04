package com.ort.dogadoption.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ort.dogadoption.models.Pets

@Dao
interface PetsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPet(pet: Pets?)

    @Query("SELECT * FROM pets ORDER BY uid")
    fun loadAllPets(): MutableList<Pets?>?
}