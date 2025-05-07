package com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.network

import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */
interface NewsApiService {
    @GET("articles.php")
    fun getNews(
        @Query("description") description: String,
        ): Call<List<News>>
}