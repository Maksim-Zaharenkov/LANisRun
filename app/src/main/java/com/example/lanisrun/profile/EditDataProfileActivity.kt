package com.example.lanisrun.profile

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lanisrun.ProfileActivity
import com.example.lanisrun.databinding.ActivityEditDataProfileBinding
import kotlinx.android.synthetic.main.activity_edit_data_profile.view.*
import java.sql.DriverManager
import java.sql.SQLException

class EditDataProfileActivity : AppCompatActivity() {
    private val databaseManager = DatabaseManager(this)

    private lateinit var binding: ActivityEditDataProfileBinding

    var dataProfileEdit: DataProfileSignIn? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditDataProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarEditDataProfile.toolbar_edit_data_profile)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    fun clickOnEditDataProfile(view: View) {
        databaseManager.openDatabase()

        var dataProfile = databaseManager.readDatabaseData()
        var login = dataProfile[0].loginP

        var password = binding.passwordEdit.text.toString()
        var nsp = binding.surnameEdit.text.toString() + " " + binding.nameEdit.text.toString() + " " + binding.patronymicEdit.text.toString()
        var address = binding.addressEdit.text.toString()

        var query = "UPDATE clients SET nsp = '$nsp', address = '$address', password = '$password' WHERE login = '$login';"

        mysqlConnection(query)

        databaseManager.deleteDatabase()

        val queryEdit = "SELECT * FROM clients WHERE login = '$login';"
        mysqlConnectionEdit(queryEdit)

        saveProfile()

        Toast.makeText(this, "Profile data changed successfully!", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
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
                stmt.executeUpdate(query)
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

    private fun saveProfile() {
        databaseManager.openDatabase()
        databaseManager.insertToDatabase(
            dataProfileEdit?.login.toString(),
            dataProfileEdit?.password.toString(),
            dataProfileEdit?.nsp.toString(),
            dataProfileEdit?.phone.toString(),
            dataProfileEdit?.address.toString()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseManager.closeDatabase()
    }

    fun mysqlConnectionEdit(query:String) {
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
                    dataProfileEdit = DataProfileSignIn(resultset.getString("nsp"), resultset.getString("phone"),
                        resultset.getString("address"), resultset.getString("login"), resultset.getString("password"))
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
}