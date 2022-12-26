package com.example.lanisrun.DbServicesInCart

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.lanisrun.shopping_cart_adapter.ShoppingCartListItem

class DbServiceInCartManager(context: Context) {
    private val databaseHelper = DbServiceInCartHelper(context)
    var database: SQLiteDatabase? = null

    fun openDatabase() {
        database = databaseHelper.writableDatabase
    }

    fun insertToDatabase(name: String,
                         cost: Float,
                         description: String
                         ) {

        val values = ContentValues().apply {
            put(DbServiceInCartName.COLUMN_NAME_NAME, name)
            put(DbServiceInCartName.COLUMN_NAME_COST, cost)
            put(DbServiceInCartName.COLUMN_NAME_DESCRIPTION, description)
        }
        database?.insert(DbServiceInCartName.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readDatabaseData(): ArrayList<ShoppingCartListItem> {
        val dataServiceInCart = ArrayList<ShoppingCartListItem>()
        val cursor = database?.query(
            DbServiceInCartName.TABLE_NAME, null, null, null,
            null, null, null)

        while (cursor?.moveToNext()!!) {
            val dataName =cursor.getString(cursor.getColumnIndex(DbServiceInCartName.COLUMN_NAME_NAME))
            val dataCost =cursor.getFloat(cursor.getColumnIndex(DbServiceInCartName.COLUMN_NAME_COST))
            var item: ShoppingCartListItem? = null
            item?.nameServiceS = dataName
            item?.moneyServiceS = dataCost
            if (item != null) {
                dataServiceInCart.add(item)
            }
        }
        cursor.close()
        return dataServiceInCart
    }

    fun closeDatabase() {
        databaseHelper.close()
    }

    fun deleteDatabase() {
        database?.delete(DbServiceInCartName.TABLE_NAME, null, null)
    }
}