package com.example.hospital.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NumberData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var doctorName: String,
    var yourName: String
)