package com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.R
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.adapter.NewsAdapter
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.database.AppDatabase
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.model.News

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */

class FavoriteActivity: AppCompatActivity(), NewsAdapter.OnItemClickListener {

    private lateinit var rvFavorite : RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        rvFavorite = findViewById(R.id.rvFavorites)
    }

    override fun onResume() {
        super.onResume()

        loadPack { news ->
            rvFavorite.adapter = NewsAdapter(news, this)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }

    private fun loadPack(onComplete: (List<News>)-> Unit) {
        val dao = AppDatabase.getInstance(this).getDao()

        onComplete(dao.getAll())
    }

    override fun onItemClick(news: News) {
        val dao = AppDatabase.getInstance(this).getDao()

        dao.delete(news)

        Toast.makeText(this, "Person  "+news.title+" deleted from favorites", Toast.LENGTH_SHORT).show()

        loadPack { news ->
            rvFavorite.adapter = NewsAdapter(news, this)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }
}