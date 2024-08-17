package com.example.a2024accgr1amar
import android.os.Parcelable
import android.os.Parcel

class BAlbum(
    var id:Int,
    var nombreAlbum:String,
    var nombreCompositor:String,
    var descripcionAlbum:String,
    var canciones: ArrayList<BCancion> = arrayListOf()) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!! ,
        parcel.createTypedArrayList(BCancion.CREATOR)!!

    ) {
    }

    override fun toString(): String {
        return nombreAlbum
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombreAlbum)
        parcel.writeString(nombreCompositor)
        parcel.writeString(descripcionAlbum)
        parcel.writeTypedList(canciones)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BAlbum> {
        override fun createFromParcel(parcel: Parcel): BAlbum {
            return BAlbum(parcel)
        }

        override fun newArray(size: Int): Array<BAlbum?> {
            return arrayOfNulls(size)
        }
    }
}