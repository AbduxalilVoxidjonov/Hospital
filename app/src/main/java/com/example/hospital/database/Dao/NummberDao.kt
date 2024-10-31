package com.example.hospital.database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hospital.database.Entity.NumberData

@Dao
interface NummberDao {

    @Query("SELECT * FROM NumberData")
    fun getAllHistory(): List<NumberData>
    @Insert
    fun setNumber(number: NumberData)

    @Delete
    fun deleted(number: NumberData)
}