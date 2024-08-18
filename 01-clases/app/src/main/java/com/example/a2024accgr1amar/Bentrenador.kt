package com.example.a2024accgr1amar
import android.os.Parcelable
import android.os.Parcel


class Bentrenador(
    var id:Int,
    var nombre:String,
    var descripcion: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "$nombre $descripcion"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bentrenador> {
        override fun createFromParcel(parcel: Parcel): Bentrenador {
            return Bentrenador(parcel)
        }

        override fun newArray(size: Int): Array<Bentrenador?> {
            return arrayOfNulls(size)
        }
    }
}