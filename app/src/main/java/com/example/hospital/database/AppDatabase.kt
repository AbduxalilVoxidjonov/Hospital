package com.example.hospital.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hospital.database.Dao.Hospital
import com.example.hospital.database.Dao.LoginDao
import com.example.hospital.database.Dao.NummberDao
import com.example.hospital.database.Dao.RegisterDao
import com.example.hospital.database.Entity.LoginEntity
import com.example.hospital.database.Entity.NumberData
import com.example.hospital.database.Entity.RegisterData
import com.example.roomdatabase54.database.Entity.HospitalData


@Database(entities = [HospitalData::class, RegisterData::class, LoginEntity::class, NumberData::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun historyHospital(): Hospital
    abstract fun registerAccaunt(): RegisterDao
    abstract fun loginAccaunt(): LoginDao
    abstract  fun number():NummberDao

    companion object {
        private var INSTANSE: AppDatabase? = null

        fun getInstanse(contex: Context): AppDatabase {
            if (INSTANSE == null) {
                INSTANSE = androidx.room.Room.databaseBuilder(
                    contex.applicationContext,
                    AppDatabase::class.java,
                    "student_database"
                ).allowMainThreadQueries()
                    .build()
            }
            return INSTANSE!!
        }

    }
}