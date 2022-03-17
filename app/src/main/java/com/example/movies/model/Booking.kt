package com.example.movies.model

data class Booking(
    var bookingID: Int,
    var userID: Int,
    var userName: String,
    var filmName: String,
    var filmDate: String,
    var ticketsNumer: String
) {

    companion object {
        const val TABLE_NAME = "Booking"
        const val COL_ID = "bookingID"
        const val COL_USER_ID = "userID"
        const val COL_USER_NAME = "userName"
        const val COL_NAME = "filmName"
        const val COL_DATE = "filmDate"
        const val COL_TICKETS = "ticketsNumer"

        const val TABLE_CREATE =
            "create table $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COL_USER_ID INTEGER , $COL_USER_NAME TEXT NOT NULL ,$COL_NAME TEXT NOT NULL , $COL_DATE TEXT NOT NULL , $COL_TICKETS TEXT NOT NULL)"
    }

}