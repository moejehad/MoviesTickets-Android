package com.example.movies.model

import android.os.Parcel
import android.os.Parcelable

data class Movie (var ID:Int ,var thub:String? , var movieName:String?, var time:String? ,
                  var description:String?, var date:String? ,var duration:String? ,var categories:String?) :
    Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ID)
        parcel.writeString(thub)
        parcel.writeString(movieName)
        parcel.writeString(time)
        parcel.writeString(description)
        parcel.writeString(date)
        parcel.writeString(duration)
        parcel.writeString(categories)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {

        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }

        const val TABLE_NAME = "Movies"
        const val COL_ID = "ID"
        const val COL_IMAGE = "thub"
        const val COL_TITLE = "movieName"
        const val COL_DESCRIPTION = "description"
        const val COL_TIME = "time"
        const val COL_DATE = "date"
        const val COL_DURATION = "duration"
        const val COL_CATEGORY = "categoriesID"

        const val TABLE_CREATE =
            "create table $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COL_IMAGE TEXT NOT NULL, $COL_TITLE TEXT NOT NULL , $COL_DESCRIPTION TEXT NOT NULL ," +
                    "$COL_TIME TEXT NOT NULL , $COL_DATE TEXT NOT NULL ,$COL_DURATION TEXT NOT NULL," +
                    "$COL_CATEGORY TEXT NOT NULL )"
    }

}
