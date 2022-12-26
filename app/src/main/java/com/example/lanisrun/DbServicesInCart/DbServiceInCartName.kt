package com.example.lanisrun.DbServicesInCart

import android.provider.BaseColumns

object DbServiceInCartName {
    const val TABLE_NAME = "service"
    const val COLUMN_NAME_NAME = "name"
    const val COLUMN_NAME_COST = "cost"
    const val COLUMN_NAME_DESCRIPTION = "description"

    const val DATABASE_VERSION = 2
    const val DATABASE_NAME = "DatabaseLANIsRun.database"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME " +
            "(${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "$COLUMN_NAME_NAME TEXT," +
            "$COLUMN_NAME_COST FLOAT," +
            "$COLUMN_NAME_DESCRIPTION TEXT)"

    const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}