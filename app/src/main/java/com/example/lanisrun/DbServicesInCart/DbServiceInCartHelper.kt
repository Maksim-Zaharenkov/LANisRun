package com.example.lanisrun.DbServicesInCart

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbServiceInCartHelper(context: Context): SQLiteOpenHelper(context, DbServiceInCartName.DATABASE_NAME,
    null, DbServiceInCartName.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DbServiceInCartName.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(DbServiceInCartName.DELETE_TABLE)
        onCreate(db)
    }
}