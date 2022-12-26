package com.example.lanisrun

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lanisrun.databinding.ActivityMainBinding
import com.example.lanisrun.profile.DataProfileSignIn
import com.example.lanisrun.profile.DatabaseManager
import java.sql.DriverManager
import java.sql.SQLException

class MainActivity : AppCompatActivity() {

    private val databaseManager = DatabaseManager(this)

    private lateinit var binding: ActivityMainBinding

    var dataProfile: DataProfileSignIn? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signeUpInYourProfile()
    }

    fun clickOnStartRegisterActivity(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun clickOnSignIn(view: View) {
        var login = binding.login.text.toString()
        var password = binding.password.text.toString()

        val queryCheck = "SELECT * FROM clients WHERE login = '$login';"
        mysqlConnectionSignIn(queryCheck)
        if (password == dataProfile?.password) {
            saveProfile()

            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ChoiceOfServicesActivity::class.java)
            startActivity(intent)
        }
        else Toast.makeText(this, "Wrong login or password!", Toast.LENGTH_SHORT).show()
    }

    private fun signeUpInYourProfile () {

        databaseManager.openDatabase()

        var dataProfileDb = databaseManager.readDatabaseData()

        if (dataProfileDb.isNotEmpty()) {
            var log = dataProfileDb[0].loginP
            val queryCheckLog = "SELECT * FROM clients WHERE login = '$log';"
            mysqlConnectionSignIn(queryCheckLog)
            if (log == dataProfile?.login) {
                val intent = Intent(this, ChoiceOfServicesActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun saveProfile() {
        databaseManager.openDatabase()
        databaseManager.insertToDatabase(
            dataProfile?.login.toString(),
            dataProfile?.password.toString(),
            dataProfile?.nsp.toString(),
            dataProfile?.phone.toString(),
            dataProfile?.address.toString()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseManager.closeDatabase()
    }

    fun mysqlConnectionSignIn(query:String) {
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
                    dataProfile = DataProfileSignIn(resultset.getString("nsp"), resultset.getString("phone"),
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