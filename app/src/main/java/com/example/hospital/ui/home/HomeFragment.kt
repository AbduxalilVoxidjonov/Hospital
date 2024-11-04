package com.example.hospital.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
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


    private var countDownTimer: CountDownTimer? = null
    private val initialTimeInMillis: Long = 180000 // Save the initial countdown time


    private var timeLeftInMillis: Long = 180000 // 3 minutes in milliseconds
    private var isPaused = false

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
                        list.add(
                            NumberData(
                                id = list.size + 1,
                                doctorName = doctorName,
                                yourName = userName
                            )
                        )
                    }
                    dialog.dismiss()
                }
                dialog.show()


            }
            btnStart.setOnClickListener {
                if (isPaused) {
                    resumeCountdown() // Resume if paused
                } else {
                    startCountdown() // Start new countdown if not paused
                }
            }

            // Set onClickListener for the pause button
            btnPause.setOnClickListener {
                pauseCountdown()
            }

            // Set onClickListener for the restart button
            restartTime.setOnClickListener {
                restartCountdown()
            }
        }
        recyclerView.notifyItemChanged(list.size)
        binding.recycleViewItem.adapter = recyclerView



        return binding.root
    }

    private fun startCountdown() {
        countDownTimer?.cancel() // Cancel any existing timer
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished // Save remaining time
                updateTimerText()
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.tvWatchTime.text = "Watch time:  00:00"
            }
        }.start()
        isPaused = false
    }

    private fun pauseCountdown() {
        countDownTimer?.cancel() // Stop the timer
        isPaused = true
    }

    private fun resumeCountdown() {
        startCountdown() // Resume the countdown with the remaining time
    }

    private fun restartCountdown() {
        // Cancel any existing countdown and reset the time
        countDownTimer?.cancel()
        timeLeftInMillis = initialTimeInMillis // Reset to initial time (3 minutes)
        updateTimerText() // Update the TextView immediately
        isPaused = false
        startCountdown() // Start the countdown from the beginning
    }

    private fun updateTimerText() {
        val minutes = timeLeftInMillis / 1000 / 60
        val seconds = timeLeftInMillis / 1000 % 60
        binding.tvWatchTime.text = String.format("Watch time:  %02d:%02d", minutes, seconds)
    }
}
