package com.example.hospital.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hospital.Adapter.AdapterHistory
import com.example.hospital.database.AppDatabase
import com.example.hospital.databinding.FragmentHistoryBinding
import com.example.roomdatabase54.database.Entity.HospitalData

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var recyclerView: AdapterHistory
    private val appDatabase by lazy {
        AppDatabase.getInstanse(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        val list = arrayListOf<HospitalData>()

        appDatabase.historyHospital().getAllHistory().forEach {
            list.add(HospitalData(it.id, it.doctorName, it.sickness))
        }

        recyclerView = AdapterHistory(list, requireContext())
        recyclerView.notifyItemChanged(list.size)

        binding.recycleView.adapter = recyclerView




        return binding.root

    }

}