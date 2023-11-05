package com.ort.dogadoption.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
class Pets( name : String , race : String, subrace: String, gender: String, age: String, image: String, weigth: Int, phone:Int): Parcelable {
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

    @ColumnInfo(name = "weigth")
    val weigth: Int

    @ColumnInfo(name = "phone")
    val phone: Int

    @ColumnInfo(name = "image")
    val image: String

    @ColumnInfo(name = "favorite")
    var favorite: Boolean? = null

    @ColumnInfo(name = "adopted")
    var adopted: Boolean? = null

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()!!,
        parcel.readInt()!!,
    ) {
        uid = parcel.readValue(Int::class.java.classLoader) as? Int
        favorite = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        adopted = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }

    init {
        this.name = name
        this.race = race
        this.subrace = subrace
        this.gender = gender
        this.age = age
        this.weigth = weigth
        this.phone = phone
        this.image = image
        this.favorite = false
        this.adopted = false
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(uid)
        parcel.writeValue(favorite)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pets> {
        override fun createFromParcel(parcel: Parcel): Pets {
            return Pets(parcel)
        }

        override fun newArray(size: Int): Array<Pets?> {
            return arrayOfNulls(size)
        }
    }
}