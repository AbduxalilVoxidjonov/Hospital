package com.example.hospital.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hospital.R
import com.example.hospital.database.AppDatabase
import com.example.hospital.databinding.FragmentAddBinding
import com.example.hospital.databinding.FragmentHomeBinding
import com.example.roomdatabase54.database.Entity.HospitalData

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val appDatabase by lazy {
        AppDatabase.getInstanse(contex = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

        binding.apply {
            btnSave.setOnClickListener {
                val name = "Doctor: "+doctorName.text.toString()
                val sicknesss = "Sickness: "+sickness.text.toString()
                if (name.isEmpty() || sicknesss.isEmpty()) {
                    Toast.makeText(requireContext(), "To'liq to'ldiring", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else {
                    Toast.makeText(requireContext(), "Saqlandi", Toast.LENGTH_SHORT).show()
                    doctorName.text?.clear()
                    sickness.text?.clear()
                    appDatabase.historyHospital()
                        .historyData(HospitalData(doctorName = name, sickness = sicknesss))
                }
            }
        }
        return binding.root
    }


}
