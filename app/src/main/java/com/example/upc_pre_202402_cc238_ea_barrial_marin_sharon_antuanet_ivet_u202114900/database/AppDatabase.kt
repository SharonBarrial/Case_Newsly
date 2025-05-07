package com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.model.News

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */

@Database(entities = [News::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): NewsDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "news.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }

}