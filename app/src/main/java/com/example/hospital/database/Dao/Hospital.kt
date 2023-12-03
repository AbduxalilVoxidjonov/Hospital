package com.example.hospital.database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomdatabase54.database.Entity.HospitalData

@Dao
interface Hospital {

    @Query("SELECT * FROM HospitalData")
    fun getAllHistory(): List<HospitalData>

    @Insert
    fun historyData(hospitalData: HospitalData)

    @Delete
    fun deleteData(hospitalData: HospitalData)
}