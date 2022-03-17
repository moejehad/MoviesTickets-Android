package com.example.movies.model

import android.os.Parcel
import android.os.Parcelable

data class User (var userID:Int , var userName:String?, var userEmail:String?,var userPhone:String?,var userPassword:String?,var userImage:String?) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userID)
        parcel.writeString(userName)
        parcel.writeString(userEmail)
        parcel.writeString(userPhone)
        parcel.writeString(userPassword)
        parcel.writeString(userImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {

        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }

        const val TABLE_NAME = "Users"
        const val COL_ID = "userID"
        const val COL_NAME = "userName"
        const val COL_EMAIL = "userEmail"
        const val COL_PHONE = "userPhone"
        const val COL_PASSWORD = "userPassword"
        const val COL_IMAGE = "userImage"

        const val TABLE_CREATE =
            "create table $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COL_NAME TEXT NOT NULL , $COL_EMAIL TEXT NOT NULL , $COL_PHONE TEXT NOT NULL," +
                    "$COL_PASSWORD TEXT NOT NULL , $COL_IMAGE TEXT)"

    }

}