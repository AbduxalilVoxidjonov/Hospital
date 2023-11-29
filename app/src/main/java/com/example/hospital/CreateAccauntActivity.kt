package com.example.hospital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hospital.databinding.ActivityCreateAccauntBinding

class CreateAccauntActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccauntBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccauntBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}