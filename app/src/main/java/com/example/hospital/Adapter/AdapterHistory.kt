package com.example.hospital.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.database.AppDatabase
import com.example.hospital.databinding.ItemHistoryBinding
import com.example.roomdatabase54.database.Entity.HospitalData


class AdapterHistory(private val list: ArrayList<HospitalData>,private val contex:Context) :
    RecyclerView.Adapter<AdapterHistory.ViewHolder>() {
    private val appDatabase by lazy {
        AppDatabase.getInstanse(contex)
    }
    // holder class to hold reference
    inner class ViewHolder(val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binds(history: HospitalData) {
            binding.apply {
                doctorName.text = history.doctorName
                sicknessName.text = history.sickness
                remove.setOnClickListener(View.OnClickListener {
                    list.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                    appDatabase.historyHospital().deleteData(history)
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHistoryBinding.inflate(
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