package com.ort.dogadoption.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
class Pets( name : String , race : String, subrace: String, gender: String, age: String, image: String) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int? = null

    @ColumnInfo(name = "name")
    val name: String

    @ColumnInfo(name = "race")
    val race: String

    @ColumnInfo(name = "subrace")
    val subrace: String

    @ColumnInfo(name = "gender")
    val gender: String

    @ColumnInfo(name = "age")
    val age: String

    @ColumnInfo(name = "image")
    val image: String


    init {
        this.name = name
        this.race = race
        this.subrace = subrace
        this.gender = gender
        this.age = age
        this.image = image
    }
}