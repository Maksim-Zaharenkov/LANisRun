package com.example.lanisrun.profile

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DatabaseManager(context: Context) {

    private val databaseHelper = DatabaseHelper(context)
    var database: SQLiteDatabase? = null

    fun openDatabase() {
        database = databaseHelper.writableDatabase
    }

    fun insertToDatabase(
        login: String,
        password: String,
        nsp: String,
        phone: String,
        address: String
    ) {

        val values = ContentValues().apply {
            put(DatabaseName.COLUMN_NAME_LOGIN, login)
            put(DatabaseName.COLUMN_NAME_PASSWORD, password)
            put(DatabaseName.COLUMN_NAME_NSP, nsp)
            put(DatabaseName.COLUMN_NAME_PHONE, phone)
            put(DatabaseName.COLUMN_NAME_ADDRESS, address)
        }
        database?.insert(DatabaseName.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readDatabaseData(): ArrayList<ProfileClass> {
        val dataProfile = ArrayList<ProfileClass>()
        val cursor = database?.query(DatabaseName.TABLE_NAME, null, null, null,
            null, null, null)

        while (cursor?.moveToNext()!!) {
            val dataLogin =cursor.getString(cursor.getColumnIndex(DatabaseName.COLUMN_NAME_LOGIN))
            val dataPassword =cursor.getString(cursor.getColumnIndex(DatabaseName.COLUMN_NAME_PASSWORD))
            val dataNSP =cursor.getString(cursor.getColumnIndex(DatabaseName.COLUMN_NAME_NSP))
            val dataPhone =cursor.getString(cursor.getColumnIndex(DatabaseName.COLUMN_NAME_PHONE))
            val dataAddress =cursor.getString(cursor.getColumnIndex(DatabaseName.COLUMN_NAME_ADDRESS))
            var item = ProfileClass()
            item.loginP = dataLogin
            item.passwordP = dataPassword
            item.nspP = dataNSP
            item.phoneP = dataPhone
            item.addressP = dataAddress
            dataProfile.add(item)
        }
        cursor.close()
        return dataProfile
    }

    fun closeDatabase() {
        databaseHelper.close()
    }

    fun deleteDatabase() {
        database?.delete(DatabaseName.TABLE_NAME, null, null)
    }
}