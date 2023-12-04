package com.example.hospital.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hospital.database.Entity.RegisterData
import com.example.roomdatabase54.database.Entity.HospitalData

@Dao
interface RegisterDao {
    @Query("SELECT * FROM RegisterData WHERE email = :email AND password = :password")
    fun getLogin(email: String, password: String): Boolean

    @Query("SELECT * FROM RegisterData where id=:id")
    fun getHistory(id: Int): RegisterData

    @Insert
    fun historyData(registerData: RegisterData)


}
