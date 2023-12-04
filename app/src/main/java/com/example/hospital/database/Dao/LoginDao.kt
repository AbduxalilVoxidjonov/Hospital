package com.example.hospital.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hospital.database.Entity.LoginEntity

@Dao
interface LoginDao {
    @Query("SELECT * FROM LoginEntity")
    fun getAllHistory(): List<LoginEntity>
    @Insert
    fun getlogin(loginEntity: LoginEntity)
}
