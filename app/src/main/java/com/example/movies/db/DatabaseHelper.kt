package com.example.movies.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.movies.R
import com.example.movies.model.Booking
import com.example.movies.model.Movie
import com.example.movies.model.User

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private var db: SQLiteDatabase

    init {
        db = writableDatabase
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(Movie.TABLE_CREATE)
        p0!!.execSQL(User.TABLE_CREATE)
        p0!!.execSQL(Booking.TABLE_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS ${Movie.TABLE_NAME}")
        p0!!.execSQL("DROP TABLE IF EXISTS ${User.TABLE_NAME}")
        p0!!.execSQL("DROP TABLE IF EXISTS ${Booking.TABLE_NAME}")
        onCreate(p0)
    }


    fun insertUser(name: String, password: String, email: String, phone: String , userImage:String): Boolean {

        val cv = ContentValues()
        cv.put(User.COL_NAME, name)
        cv.put(User.COL_EMAIL, email)
        cv.put(User.COL_PHONE, phone)
        cv.put(User.COL_PASSWORD, password)
        cv.put(User.COL_IMAGE, userImage)

        return db.insert(User.TABLE_NAME, null, cv) > 0

    }


    fun checkPhone(phone: String): Boolean {
        val c = db.rawQuery(
            "select ${User.COL_PHONE} from ${User.TABLE_NAME} where ${User.COL_PHONE} = '$phone' ",
            null
        )
        return c.count > 0
        c.close()
    }


    fun loginCheck(phone: String, password: String): Boolean {
        val c = db.rawQuery(
            "select ${User.COL_PHONE},${User.COL_PASSWORD} from ${User.TABLE_NAME} where ${User.COL_PHONE} = '$phone' " +
                    "and ${User.COL_PASSWORD} = '$password' ",
            null
        )
        return c.count > 0
    }


    fun getUser(phone: String): ArrayList<User> {
        var userData = ArrayList<User>()
        val c = db.rawQuery(
            "select * from ${User.TABLE_NAME} where ${User.COL_PHONE} = '$phone' limit 1",
            null
        )
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = User(
                c.getInt(0),
                c.getString(1),
                c.getString(2),
                c.getString(3),
                c.getString(4),
                c.getString(5)
            )
            userData.add(s)
            c.moveToNext()
        }
        c.close()
        return userData
    }


    fun updateUser(oldID: Int, name: String, password: String, email: String, phone: String , image:String) : Boolean {
        val cv = ContentValues()
        cv.put(User.COL_NAME, name)
        cv.put(User.COL_EMAIL, email)
        cv.put(User.COL_PHONE, phone)
        cv.put(User.COL_PASSWORD, password)
        cv.put(User.COL_IMAGE, image)

        return db.update(User.TABLE_NAME,cv,"${User.COL_ID} == $oldID",null) > 0
    }


    /*---------------------------------------------------*/

    fun insertMovie(
        movieName: String, time: String,
        description: String, date: String, duration: String, categories: String , MovieImage:String
    ): Boolean {
        val cv = ContentValues()
        cv.put(Movie.COL_IMAGE, MovieImage)
        cv.put(Movie.COL_TITLE, movieName)
        cv.put(Movie.COL_TIME, time)
        cv.put(Movie.COL_DESCRIPTION, description)
        cv.put(Movie.COL_DATE, date)
        cv.put(Movie.COL_DURATION, duration)
        cv.put(Movie.COL_CATEGORY, categories)

        return db.insert(Movie.TABLE_NAME, null, cv) > 0
    }

    fun getAllMovies(arrang: String, limit: Int): ArrayList<Movie> {

        var moviesList = ArrayList<Movie>()
        val c = db.rawQuery(
            "select * from ${Movie.TABLE_NAME} order by ${Movie.COL_ID} $arrang limit $limit",
            null
        )
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Movie(
                c.getInt(0),
                c.getString(1),
                c.getString(2),
                c.getString(3),
                c.getString(4),
                c.getString(5),
                c.getString(6),
                c.getString(7)
            )
            moviesList.add(s)
            c.moveToNext()
        }
        c.close()
        return moviesList
    }

    fun getCategoriesMovies(category: String): ArrayList<Movie> {

        var CategoriesMovies = ArrayList<Movie>()
        val c = db.rawQuery(
            "select * from ${Movie.TABLE_NAME} where ${Movie.COL_CATEGORY} = '$category' order by ${Movie.COL_ID} desc limit 4",
            null
        )
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Movie(
                c.getInt(0),
                c.getString(1),
                c.getString(2),
                c.getString(3),
                c.getString(4),
                c.getString(5),
                c.getString(6),
                c.getString(7)
            )
            CategoriesMovies.add(s)
            c.moveToNext()
        }
        c.close()
        return CategoriesMovies
    }


    fun deleteMovie(id: Int): Boolean {
        return db.delete(Movie.TABLE_NAME, "${Movie.COL_ID} == $id", null) > 0
    }


    /*---------------------------------------------------*/


    fun insertBooking(userID:Int , userName:String, filmName: String , filmDate:String, ticketsNumer:String): Boolean {

        val cv = ContentValues()
        cv.put(Booking.COL_USER_ID, userID)
        cv.put(Booking.COL_USER_NAME, userName)
        cv.put(Booking.COL_NAME, filmName)
        cv.put(Booking.COL_DATE, filmDate)
        cv.put(Booking.COL_TICKETS, ticketsNumer)

        return db.insert(Booking.TABLE_NAME, null, cv) > 0

    }

    fun getAllBooking (usId:Int): ArrayList<Booking> {

        var bookingList = ArrayList<Booking>()
        val c = db.rawQuery(
            "select * from ${Booking.TABLE_NAME} where ${Booking.COL_USER_ID} = $usId order by ${Booking.COL_ID} desc",
            null
        )
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Booking(
                c.getInt(0),
                c.getInt(1),
                c.getString(2),
                c.getString(3),
                c.getString(4),
                c.getString(5) + " Ticket"
            )
            bookingList.add(s)
            c.moveToNext()
        }
        c.close()
        return bookingList
    }


    /*--------------------------------------------------------*/


    companion object {
        const val DATABASE_NAME = "Movies"
        const val DATABASE_VERSION = 2
    }


}

