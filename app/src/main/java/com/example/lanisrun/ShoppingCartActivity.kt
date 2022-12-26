package com.example.lanisrun

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lanisrun.DbServicesInCart.DbServiceInCartManager
import com.example.lanisrun.databinding.ActivityShoppingCartBinding
import com.example.lanisrun.shopping_cart_adapter.ShoppingCartListItem
import kotlinx.android.synthetic.main.activity_shopping_cart.view.*

class ShoppingCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingCartBinding

    private val databaseManager = DbServiceInCartManager(this)

    var list = ArrayList<ShoppingCartListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarShoppingCart.toolbar_shopping_cart)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        databaseManager.openDatabase()
//
//        var dataServiceDb = databaseManager.readDatabaseData()

//        if (dataServiceDb.isNotEmpty()) {
//            binding.textViewShoppingCartEmpty.visibility = View.GONE
//            binding.textViewShoppingCartHelp.visibility = View.GONE
//            binding.buttonShoppingCart.visibility = View.GONE
//            binding.rcInShoppingCartActivity.rcViewShoppingCart.visibility = View.VISIBLE
//        }
//        var i = 0
//        while (i != dataServiceDb.size - 1) {
//            list[i].nameServiceS = dataServiceDb[i].nameC
//            list[i].moneyServiceS = dataServiceDb[i].costC
//        }
//
//        rcView.hasFixedSize()
//        rcView.layoutManager = LinearLayoutManager(this)
//        rcView.adapter = ShoppingCartAdapter(list, this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            val intent = Intent(this, ChoiceOfServicesActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    fun clickOnChoiceOfServices(view: View) {
        val intent = Intent(this, ChoiceOfServicesActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseManager.closeDatabase()
    }
}