package com.example.roomdatabase54.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HospitalData(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var doctorName: String,
    var sickness: String
)
