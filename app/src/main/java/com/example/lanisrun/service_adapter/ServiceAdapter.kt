package com.example.lanisrun.service_adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lanisrun.R

class ServiceAdapter(listArray: ArrayList<ServiceListItem>, context: Context): RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {
    var listArrayR = listArray
    var contextR = context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.services_item_name)
        val tvMoney = view.findViewById<TextView>(R.id.services_item_money)
//        val plusButton = view.findViewById<FloatingActionButton>(R.id.add_service_in_cart)
        var id: Int? = null
        var idService: Int? = null

        fun bind(listItem: ServiceListItem, context: Context) {
//            val databaseManager = DatabaseManager(context)
            tvName.text = listItem.nameService
            tvMoney.text = "Service cost: " + listItem.moneyService.toString() + "$"

//            plusButton.setOnClickListener {
//                databaseManager.openDatabase()
//                var dataProfile = databaseManager.readDatabaseData()
//                var clientLogin = dataProfile[0].loginP
//                var status = "No"
//                val queryCheckS = "SELECT * FROM services WHERE name = '${listItem.nameService}';"
//                mysqlConnectionCheckService(queryCheckS)
//                val queryCheck = "SELECT * FROM `shopping cart` WHERE `client_id` = '$clientLogin' AND `status` = '$status';"
//                mysqlConnectionCheck(queryCheck)
//                if (id != null) {
//                    var query = "INSERT INTO `shopping cart` (`id_cart`, `service_id`, `client_id`, `status`) VALUES ('$id', $idService, '$clientLogin', '$status');"
//
//                    mysqlConnection(query)
//                }
//                else {
//                    var query = "INSERT INTO `shopping cart` (`service_id`, `client_id`, `status`) VALUES ('$idService', '$clientLogin', '$status');"
//
//                    mysqlConnection(query)
//                }
//            }

            itemView.setOnClickListener() {
                val intent = Intent(context, ContentItemService::class.java).apply {
                    putExtra("service name", tvName.text.toString())
                }
                context.startActivity(intent)
            }
        }

//        fun mysqlConnectionCheckService(query:String) {
//            try {
//                Class.forName("com.mysql.jdbc.Driver").newInstance()
//
//                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//                StrictMode.setThreadPolicy(policy)
//
//                val conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3489796",
//                    "sql3489796", "2vxvlywCMF")
//
//                if (conn != null){
//                    var stmt = conn.createStatement()
//                    var resultset = stmt.executeQuery(query)
//                    resultset = stmt.resultSet
//                    while (resultset!!.next()) {
//                        idService = resultset.getInt("id")
//                    }
//                }
//
//                conn.close()
//
//            } catch (ex: SQLException) {
//                // handle any errors
//                ex.printStackTrace()
//            } catch (ex: Exception) {
//                // handle any errors
//                ex.printStackTrace()
//            }
//        }
//
//        fun mysqlConnectionCheck(query:String) {
//            try {
//                Class.forName("com.mysql.jdbc.Driver").newInstance()
//
//                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//                StrictMode.setThreadPolicy(policy)
//
//                val conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3489796",
//                    "sql3489796", "2vxvlywCMF")
//
//                if (conn != null){
//                    var stmt = conn.createStatement()
//                    var resultset = stmt.executeQuery(query)
//                    resultset = stmt.resultSet
//                    while (resultset!!.next()) {
//                        id = resultset.getInt("id_cart")
//                    }
//                }
//
//                conn.close()
//
//            } catch (ex: SQLException) {
//                // handle any errors
//                ex.printStackTrace()
//            } catch (ex: Exception) {
//                // handle any errors
//                ex.printStackTrace()
//            }
//        }
//
//        fun mysqlConnection(query:String) {
//            try {
//                Class.forName("com.mysql.jdbc.Driver").newInstance()
//
//                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//                StrictMode.setThreadPolicy(policy)
//
//                val conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3489796",
//                    "sql3489796", "2vxvlywCMF")
//
//                if (conn != null){
//                    var stmt = conn.createStatement()
//                    stmt.executeUpdate(query)
//                }
//
//                conn.close()
//
//            } catch (ex: SQLException) {
//                // handle any errors
//                ex.printStackTrace()
//            } catch (ex: Exception) {
//                // handle any errors
//                ex.printStackTrace()
//            }
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextR)
        return ViewHolder(inflater.inflate(R.layout.item_services, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItem = listArrayR[position]
        holder.bind(listItem, contextR)
    }

    override fun getItemCount(): Int {
        return listArrayR.size
    }
}