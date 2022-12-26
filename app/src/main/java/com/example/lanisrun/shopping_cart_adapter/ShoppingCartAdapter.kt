package com.example.lanisrun.shopping_cart_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lanisrun.R

class ShoppingCartAdapter(listArray: ArrayList<ShoppingCartListItem>, context: Context): RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
    var listArrayRSC = listArray
    var contextRSC = context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvNameSC = view.findViewById<TextView>(R.id.shopping_cart_item_name)
        val tvMoneySC = view.findViewById<TextView>(R.id.shopping_cart_item_money)

        fun bind(listItem: ShoppingCartListItem, context: Context) {
            tvNameSC.text = listItem.nameServiceS
            tvMoneySC.text = "Стоимость услуги: " + listItem.moneyServiceS.toString() + "$"

            itemView.setOnClickListener() {
//                val intent = Intent(context, EditServicesActivity::class.java)
//                startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextRSC)
        return ViewHolder(inflater.inflate(R.layout.item_shopping_cart, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItemSC = listArrayRSC[position]
        holder.bind(listItemSC, contextRSC)
    }

    override fun getItemCount(): Int {
        return listArrayRSC.size
    }
}