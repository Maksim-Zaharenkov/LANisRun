package com.example.lanisrun

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.lanisrun.databinding.ActivityProfileBinding
import com.example.lanisrun.profile.DatabaseManager
import com.example.lanisrun.profile.EditDataProfileActivity
import kotlinx.android.synthetic.main.activity_profile.view.*

class ProfileActivity : AppCompatActivity() {
    private val databaseManager = DatabaseManager(this)

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarProfile.toolbar_profile)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        databaseManager.openDatabase()

        var dataProfile = databaseManager.readDatabaseData()

        binding.textViewProfileNSP.text = dataProfile[0].nspP
        binding.textViewProfileAddress.text = dataProfile[0].addressP
        binding.textViewProfilePhone.text = dataProfile[0].phoneP
        binding.buttonProfile.setOnClickListener {
            val intent = Intent(this, EditDataProfileActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            val intent = Intent(this, ChoiceOfServicesActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseManager.closeDatabase()
    }
}