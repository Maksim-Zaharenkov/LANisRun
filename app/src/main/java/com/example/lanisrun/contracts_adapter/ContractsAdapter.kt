package com.example.lanisrun.contracts_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lanisrun.R

class ContractsAdapter(listArray: ArrayList<ContractsListItem>, context: Context): RecyclerView.Adapter<ContractsAdapter.ViewHolder>() {

    var listArrayR = listArray
    var contextR = context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvId = view.findViewById<TextView>(R.id.contracts_item_code)
        var tvSpecialistPhone = view.findViewById<TextView>(R.id.worker_nsp_item)
        var tvDateWriting = view.findViewById<TextView>(R.id.date_write_item)
        var tvPeriod = view.findViewById<TextView>(R.id.period_item)
        var tvCost = view.findViewById<TextView>(R.id.money_contract_item)
        var tvNameServices = view.findViewById<TextView>(R.id.status_item)

        fun bind(listItem: ContractsListItem, context: Context) {

            tvId.text = "Contract № " + listItem.id.toString()
            tvSpecialistPhone.text = "Phone number of specialist: " + listItem.specialistPhone
            tvDateWriting.text = "Date writing: " + listItem.dateWriting
            tvPeriod.text = "Period: " + listItem.period
            tvCost.text = "Contract cost: " + listItem.cost.toString() + "$"
            tvNameServices.text = "Services: " + listItem.nameServices
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextR)
        return ViewHolder(inflater.inflate(R.layout.item_contracts, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItem = listArrayR[position]
        holder.bind(listItem, contextR)
    }

    override fun getItemCount(): Int {
        return listArrayR.size
    }
}