package com.example.inspire12.retrofitproject.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo() :Parcelable{

    @SerializedName("date_taken")
    @Expose
    var dateTaken: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("width")
    @Expose
    var width: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("height")
    @Expose
    var height: String? = null

    constructor(parcel: Parcel) : this() {
        dateTaken = parcel.readString()
        title = parcel.readString()
        width = parcel.readString()
        url = parcel.readString()
        height = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dateTaken)
        parcel.writeString(title)
        parcel.writeString(width)
        parcel.writeString(url)
        parcel.writeString(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }

}