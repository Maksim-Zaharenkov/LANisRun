package com.example.lanisrun.service_adapter

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.lanisrun.ChoiceOfServicesActivity
import com.example.lanisrun.databinding.ActivityContentItemServiceBinding
import kotlinx.android.synthetic.main.activity_content_item_service.view.*
import java.sql.DriverManager
import java.sql.SQLException

class ContentItemService : AppCompatActivity() {

    var content: ServiceListItem? = null

    private lateinit var binding: ActivityContentItemServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContentItemServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarContentService.toolbar_content_service)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val key = intent.getStringExtra("service name")

        val query = "SELECT * FROM services WHERE name = '$key'"

        mysqlConnection(query)

        binding.servicesContentName.text = content?.nameService.toString()
        binding.servicesContentMoney.text = content?.moneyService.toString() + "$"
        binding.servicesContentDescription.text = content?.descriptionService.toString()
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

                    content = ServiceListItem(resultset.getString("name"), resultset.getFloat("money"),
                        resultset.getString("description"))
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, ChoiceOfServicesActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}