package com.example.hospital.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.hospital.Adapter.AdapterHistory
import com.example.hospital.Adapter.AdapterItem
import com.example.hospital.R
import com.example.hospital.database.AppDatabase
import com.example.hospital.database.Entity.NumberData
import com.example.hospital.databinding.FragmentHomeBinding
import com.example.roomdatabase54.database.Entity.HospitalData
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var recyclerView: AdapterItem
    private val appDatabase by lazy {
        AppDatabase.getInstanse(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val list = arrayListOf<NumberData>()
        appDatabase.number().getAllHistory().forEach {
            list.add(NumberData(it.id, it.doctorName, it.yourName))
        }

        recyclerView = AdapterItem(list, requireContext())
        recyclerView.notifyItemChanged(list.size)

        binding.recycleViewItem.adapter = recyclerView

        binding.apply {
            addNavbat.setOnClickListener {
                val dialog = Dialog(requireContext())
                dialog.setContentView(R.layout.dialog)
                val doctorNameEditText = dialog.findViewById<EditText>(R.id.doctornames)
                val userNameEditText = dialog.findViewById<EditText>(R.id.yournames)

                val button = dialog.findViewById<View>(R.id.cancel_button)
                button
                    .setOnClickListener {
                        dialog.dismiss()
                    }
                val savebutton = dialog.findViewById<View>(R.id.save_button)
                savebutton.setOnClickListener {
                    val doctorName = doctorNameEditText.text.toString()
                    val userName = userNameEditText.text.toString()

                    // Save data to Room database using a coroutine
                    lifecycleScope.launch {
                        val doctorInfo = NumberData(doctorName = doctorName, yourName = userName)
                        appDatabase.number().setNumber(doctorInfo)
                        list.add(NumberData(id =list.size+1 ,  doctorName = doctorName, yourName = userName))
                    }
                    dialog.dismiss()
                }
                dialog.show()


            }

        }
        recyclerView.notifyItemChanged(list.size)
        binding.recycleViewItem.adapter = recyclerView

        return binding.root
    }


}