package com.example.hospital.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.database.AppDatabase
import com.example.hospital.database.Entity.NumberData
import com.example.hospital.databinding.ItemHistoryBinding
import com.example.hospital.databinding.ItemNumberBinding
import com.example.roomdatabase54.database.Entity.HospitalData

class AdapterItem(private val list: ArrayList<NumberData>, private val contex: Context) :
    RecyclerView.Adapter<AdapterItem.ViewHolder>() {
    private val appDatabase by lazy {
        AppDatabase.getInstanse(contex)
    }

    // holder class to hold reference
    inner class ViewHolder(val binding: ItemNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binds(history: NumberData) {
            binding.apply {
                doctorNameItem.text = "Doctor: " + history.doctorName
                yournameItem.text = "Your: " + history.yourName
                itemNumber.text = "Id: " + history.id
                remove.setOnClickListener(View.OnClickListener {
                    list.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                    appDatabase.number().deleted(history)
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNumberBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binds(list[position])
    }
}