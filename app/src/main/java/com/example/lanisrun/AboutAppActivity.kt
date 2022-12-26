package com.example.lanisrun

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.lanisrun.databinding.ActivityAboutAppBinding
import kotlinx.android.synthetic.main.activity_about_app.view.*

class AboutAppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarAboutApp.toolbar_about_app)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            val intent = Intent(this, ChoiceOfServicesActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}