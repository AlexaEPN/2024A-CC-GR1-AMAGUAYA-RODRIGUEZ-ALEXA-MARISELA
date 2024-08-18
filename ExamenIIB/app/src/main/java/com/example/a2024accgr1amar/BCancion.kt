package com.example.a2024accgr1amar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable

class BCancion(
    var idCancion:Int,
    var nombreCancion:String,
    var duracionCancion:String,
    var reproducciones: Float,
    var meGusta: Float): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readFloat(),
        parcel.readFloat()
    ) {
    }

    override fun toString(): String {
        return nombreCancion
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idCancion)
        parcel.writeString(nombreCancion)
        parcel.writeString(duracionCancion)
        parcel.writeFloat(reproducciones)
        parcel.writeFloat(meGusta)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BCancion> {
        override fun createFromParcel(parcel: Parcel): BCancion {
            return BCancion(parcel)
        }

        override fun newArray(size: Int): Array<BCancion?> {
            return arrayOfNulls(size)
        }
    }
}