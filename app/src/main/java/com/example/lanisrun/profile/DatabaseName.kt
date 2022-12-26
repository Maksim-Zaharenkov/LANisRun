package com.example.lanisrun.profile

import android.provider.BaseColumns

object DatabaseName {

    const val TABLE_NAME = "profile"
    const val COLUMN_NAME_LOGIN = "login"
    const val COLUMN_NAME_PASSWORD = "password"
    const val COLUMN_NAME_NSP = "nsp"
    const val COLUMN_NAME_PHONE = "phone"
    const val COLUMN_NAME_ADDRESS = "address"

    const val DATABASE_VERSION = 2
    const val DATABASE_NAME = "DatabaseLANIsRun.database"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME " +
            "(${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "$COLUMN_NAME_LOGIN TEXT," +
            "$COLUMN_NAME_PASSWORD TEXT," +
            "$COLUMN_NAME_NSP TEXT," +
            "$COLUMN_NAME_PHONE TEXT," +
            "$COLUMN_NAME_ADDRESS TEXT)"

    const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}