package com.example.lanisrun

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lanisrun.databinding.ActivityRegisterBinding
import java.sql.DriverManager
import java.sql.SQLException

class RegisterActivity : AppCompatActivity() {

    var phoneCheck: String? = null
    var loginCheck: String? = null

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun clickOnRegister(view: View) {
        var phone = binding.phone.text.toString()
        val queryCheckPhone = "SELECT * FROM clients WHERE phone = '$phone';"
        mysqlConnectionChekRegister(queryCheckPhone)

        if (phone != phoneCheck) {
            var login = binding.login.text.toString()
            val queryCheckLogin = "SELECT * FROM clients WHERE login = '$login';"
            mysqlConnectionChekRegister(queryCheckLogin)

            if (login != loginCheck) {

                var password = binding.password.text.toString()
                var nsp = binding.surname.text.toString() + " " + binding.name.text.toString() + " " + binding.patronymic.text.toString()
                var address = binding.address.text.toString()

                var query = "INSERT INTO clients (nsp, phone, address, login, password) VALUES ('$nsp', $phone, '$address', '$login', '$password');"

                mysqlConnection(query)

                Toast.makeText(this, "You are registered successfully!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else Toast.makeText(this, "Choose another login!", Toast.LENGTH_SHORT).show()
        }
        else Toast.makeText(this, "This phone number is already in use!", Toast.LENGTH_SHORT).show()
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

    fun mysqlConnectionChekRegister(query:String) {
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
                    phoneCheck = resultset.getString("phone")
                    loginCheck = resultset.getString("login")
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