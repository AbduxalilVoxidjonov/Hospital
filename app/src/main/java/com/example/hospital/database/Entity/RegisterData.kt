package com.example.hospital.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RegisterData(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var name: String,
    var surname: String,
    var email: String,
    var password: String,
    var data:String,
    var location:String,
    var phone: String
)
