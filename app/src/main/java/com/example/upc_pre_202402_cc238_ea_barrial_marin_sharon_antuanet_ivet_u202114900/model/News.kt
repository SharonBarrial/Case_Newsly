package com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */

@Entity
data class News (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name="publishedAt")
    val publishedAt: String?,

    @ColumnInfo(name="author")
    val author: String?,

    @ColumnInfo(name="title")
    val title: String?,

    @ColumnInfo(name="description")
    val description: String?,

    @ColumnInfo(name="url")
    val url: String?,

    @ColumnInfo(name="urlToImage")
    val urlToImage: String?,
)