package com.example.lanisrun

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lanisrun.contracts_adapter.ContractsAdapter
import com.example.lanisrun.contracts_adapter.ContractsListItem
import com.example.lanisrun.databinding.ActivityMyContractsBinding
import com.example.lanisrun.profile.DatabaseManager
import kotlinx.android.synthetic.main.activity_my_contracts.view.*
import kotlinx.android.synthetic.main.contract_content.*
import java.sql.DriverManager
import java.sql.SQLException

class MyContractsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyContractsBinding

    var list = ArrayList<ContractsListItem>()

    private val databaseManager = DatabaseManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyContractsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMyContracts.toolbar_my_contracts)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        databaseManager.openDatabase()

        var dataProfileDb = databaseManager.readDatabaseData()
        var phone = dataProfileDb[0].phoneP

        val query = "SELECT * FROM contracts WHERE client_phone = '$phone';"

        mysqlConnection(query)

        rcViewContracts.hasFixedSize()
        rcViewContracts.layoutManager = LinearLayoutManager(this)
        rcViewContracts.adapter = ContractsAdapter(list, this)

        if (list.isEmpty()) {
            binding.textViewShoppingCartEmpty.visibility = View.VISIBLE
            binding.textViewShoppingCartHelp.visibility = View.VISIBLE
            binding.content.rcViewContracts.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            val intent = Intent(this, ChoiceOfServicesActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    fun mysqlConnection(query:String) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()

            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val conn = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3489796",
                "sql3489796", "2vxvlywCMF")

            if (conn != null){
                var stmt = conn.createStatement()
                var resultset = stmt.executeQuery(query)
                resultset = stmt.resultSet
                while (resultset!!.next()) {
                    list.add(ContractsListItem(resultset.getInt("id"), resultset.getString("specialist_phone"),
                        resultset.getString("date_writing"), resultset.getString("period"),
                        resultset.getFloat("total_cost"), resultset.getString("name_services")))
                }
            }

            conn.close()

        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseManager.closeDatabase()
    }
}