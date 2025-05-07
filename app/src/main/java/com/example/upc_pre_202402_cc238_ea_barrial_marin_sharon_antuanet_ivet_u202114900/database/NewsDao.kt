package com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.model.News

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */

@Dao
interface NewsDao {

    @Insert
    fun insert(news: News)

    @Query("SELECT * FROM news")
    fun getAll(): List<News>

    @Delete
    fun delete(news: News)

    @Update
    fun update(news: News)
}