package com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.activities.FavoriteActivity
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.activities.NewsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var findBtn : Button
    private lateinit var favoriteBtn: Button

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findBtn = findViewById(R.id.btFind)
        favoriteBtn = findViewById(R.id.btFavorite)

        findBtn.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }

        favoriteBtn.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }
}