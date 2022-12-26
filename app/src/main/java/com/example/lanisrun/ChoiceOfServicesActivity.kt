package com.example.lanisrun

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lanisrun.databinding.ActivityPersonalPageBinding
import com.example.lanisrun.profile.DatabaseManager
import com.example.lanisrun.service_adapter.ServiceAdapter
import com.example.lanisrun.service_adapter.ServiceListItem
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_personal_page.*
import kotlinx.android.synthetic.main.activity_personal_page.view.*
import kotlinx.android.synthetic.main.service_content.*
import java.sql.DriverManager
import java.sql.SQLException

class ChoiceOfServicesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val databaseManager = DatabaseManager(this)

    var list = ArrayList<ServiceListItem>()

    private lateinit var binding: ActivityPersonalPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPersonalPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val tool = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open, R.string.close)
        tool.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val query = "SELECT * FROM services"

        mysqlConnection(query)

        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = ServiceAdapter(list, this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.item_choice_of_services -> {
                val intent = Intent(this, ChoiceOfServicesActivity::class.java)
                startActivity(intent)
            }
            R.id.item_shopping_cart -> {
                val intent = Intent(this, ShoppingCartActivity::class.java)
                startActivity(intent)
            }
            R.id.item_my_contracts -> {
                val intent = Intent(this, MyContractsActivity::class.java)
                startActivity(intent)
            }
            R.id.item_about_app -> {
                val intent = Intent(this, AboutAppActivity::class.java)
                startActivity(intent)
            }
            R.id.item_exit -> {
                exitProfile()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.profile_menu_item -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.about_app_menu_item -> {
                val intent = Intent(this, AboutAppActivity::class.java)
                startActivity(intent)
            }
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
                    list.add(ServiceListItem(resultset.getString("name"), resultset.getFloat("money"), resultset.getString("description")))
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

    private fun exitProfile() {
        databaseManager.openDatabase()
        databaseManager.deleteDatabase()
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseManager.closeDatabase()
    }
}