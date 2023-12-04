package com.example.hospital.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var email: String,
    var passwor: String,
)
