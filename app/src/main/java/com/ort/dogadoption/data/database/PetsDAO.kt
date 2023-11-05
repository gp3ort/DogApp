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
    fun loadAllPets(): MutableList<Pets>

    @Query("SELECT * FROM pets WHERE favorite == 1 ORDER BY uid")
    fun loadAllFavoritesPets(): MutableList<Pets>

    @Query("UPDATE pets SET favorite = :isFavorite WHERE uid = :petId")
    fun updateFavoritePet(petId: Int, isFavorite: Boolean): Int
}