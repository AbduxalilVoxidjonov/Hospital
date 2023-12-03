package com.example.hospital

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.hospital.database.AppDatabase
import com.example.hospital.database.Entity.RegisterData
import com.example.hospital.databinding.ActivityCreateAccauntBinding

class CreateAccauntActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccauntBinding
    private val appDatabase by lazy {
        AppDatabase.getInstanse(this@CreateAccauntActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccauntBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveprogress.isVisible = false
        binding.apply {
            btnSave.setOnClickListener {
                if (yourname.text.toString().isEmpty() || yoursurname.text.toString()
                        .isEmpty() || emailadress.text.toString()
                        .isEmpty() || password.text.toString().isEmpty() || number.text.toString()
                        .isEmpty() || location.text.toString().isEmpty() || birthday.text.toString()
                        .isEmpty()
                ) {
                   return@setOnClickListener Toast.makeText(
                        this@CreateAccauntActivity,
                        "Barcha ma'lumotlar ishonchilik bilan to'ldirilishi kerak",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    appDatabase.registerAccaunt().historyData(
                        RegisterData(
                            name = yourname.text.toString(),
                            email = emailadress.text.toString(),
                            password = password.text.toString(),
                            phone = number.text.toString(),
                            location = location.text.toString(),
                            surname = yoursurname.text.toString(),
                            data = birthday.text.toString()
                        )
                    )
                    Toast.makeText(
                        this@CreateAccauntActivity,
                        "Save",
                        Toast.LENGTH_SHORT
                    ).show()
                    // clear text
                    yourname.text?.clear()
                    emailadress.text?.clear()
                    password.text?.clear()
                    number.text?.clear()
                    location.text?.clear()
                    yoursurname.text?.clear()
                    birthday.text?.clear()
                    val handler = Handler(Looper.getMainLooper())
                    saveprogress.isVisible = true
                    handler.postDelayed({
                        val intent = Intent(
                            this@CreateAccauntActivity,
                            LoginActivity::class.java
                        )
                        startActivity(intent)
                        finish()

                    }, 2000)
                }
            }
        }
    }
}