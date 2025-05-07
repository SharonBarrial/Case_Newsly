package com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.R
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.adapter.NewsAdapter
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.database.AppDatabase
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.model.News
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.network.NewsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */

class NewsActivity: AppCompatActivity(), NewsAdapter.OnItemClickListener {

    lateinit var news: List<News>
    lateinit var newsAdapter: NewsAdapter
    private lateinit var detailsBtn: Button

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news)

        /**detailsBtn = findViewById(R.id.btDetails)
        detailsBtn.setOnClickListener {
            Toast.makeText(this, "Details", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }**/

        newsAdapter = NewsAdapter(emptyList(), this)

        val btSearch = findViewById<Button>(R.id.btSearch)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.tbNews)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val rvNews  = findViewById<RecyclerView>(R.id.rvNews)
        rvNews.adapter = newsAdapter
        rvNews.layoutManager = LinearLayoutManager(this)

        btSearch.setOnClickListener {
            searchNews()
        }

    }

    override fun onItemClick(news: News) {
        val dao = AppDatabase.getInstance(this).getDao()
        dao.insert(news)

        Toast.makeText(this, "News "+news.title+" added to favorites", Toast.LENGTH_SHORT).show()
    }

    private fun searchNews() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev.formandocodigo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val newService = retrofit.create(NewsApiService::class.java)

        val etKeyword = findViewById<EditText>(R.id.etNew).text.toString()

        val request = newService.getNews(etKeyword)

        // Make the request to the server
        request.enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if (response.isSuccessful && response.body() != null) {
                    news = response.body()!!

                    val rvNews = findViewById<RecyclerView>(R.id.rvNews)

                    newsAdapter = NewsAdapter(news, this@NewsActivity)
                    rvNews.adapter = newsAdapter

                    rvNews.layoutManager = LinearLayoutManager(this@NewsActivity)
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                // Manage failure
                Log.e("PackagesActivity", "onFailure: ${t.message}")
                Toast.makeText(this@NewsActivity, "Failed to load articles: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { // ID de la flecha de retroceso
                finish() // O puedes usar 'onBackPressed()' para volver a la actividad anterior
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}


