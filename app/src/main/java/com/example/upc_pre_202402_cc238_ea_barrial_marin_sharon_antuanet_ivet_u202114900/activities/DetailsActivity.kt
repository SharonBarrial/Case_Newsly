package com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.R
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.adapter.NewsAdapter
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.database.AppDatabase
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.model.News

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */
class DetailsActivity: AppCompatActivity(), NewsAdapter.OnItemClickListener {

    private lateinit var details: News

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.activity_details)

        details = intent.getSerializableExtra("details") as News

        val tvPublishedAt: TextView = findViewById(R.id.tvYearDetails)
        val tvAuthor: TextView = findViewById(R.id.tvAuthorDetails)
        val tvTitle: TextView = findViewById(R.id.tvTittleDetails)
        val tvDescription: TextView = findViewById(R.id.tvDescriptionDetails)
        val ivPicture: ImageView = findViewById(R.id.ivPictureDetails)

        tvPublishedAt.text = details.publishedAt
        tvAuthor.text = details.author
        tvTitle.text = details.title
        tvDescription.text = details.description

        if(details.urlToImage.isNullOrEmpty()) {
            ivPicture.setImageResource(R.drawable.newspaper)
        } else {
            Glide.with(this).load(details.urlToImage).into(ivPicture)
        }
    }

    override fun onItemClick(new: News) {
        //FavoriteActivity.addFavorites(this, pack)
        val dao = AppDatabase.getInstance(this).getDao()
        dao.insert(new)

        Toast.makeText(this, "Article "+new.title+" added to favorites", Toast.LENGTH_SHORT).show()
    }

}