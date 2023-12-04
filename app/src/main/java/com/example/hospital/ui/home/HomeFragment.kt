package com.example.hospital.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hospital.R
import com.example.hospital.database.AppDatabase
import com.example.hospital.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val appDatabase by lazy {
        AppDatabase.getInstanse(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)



        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val data = appDatabase.registerAccaunt().getHistory(1)
            Log.d("TAG", "onViewCreated: $data")
            yourName.text = "Your name: " + data.name
            emailAddress.text = "Email address: " + data.email
            location.text = "Location: " + data.location
            phoneNumber.text = "Phone number: " + data.phone
            Surname.text = "Surname: " + data.surname
            password.text = "Password: " + data.password
            birthday.text = "Birthday: " + data.data
        }
    }

}