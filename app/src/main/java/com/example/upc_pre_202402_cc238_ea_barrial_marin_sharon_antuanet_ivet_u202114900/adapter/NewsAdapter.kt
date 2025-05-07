package com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.R
import com.example.upc_pre_202402_cc238_ea_barrial_marin_sharon_antuanet_ivet_u202114900.model.News

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */
class NewsAdapter(private val news: List<News>, private val clickListener: OnItemClickListener)
    : Adapter<NewsAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(itemView: View) : ViewHolder(itemView) {
        private val tvPublishedAdt: TextView = itemView.findViewById(R.id.tvYear)
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTittle)
        private val btnLink: Button = itemView.findViewById(R.id.btDetails)
        private val ivPicture: ImageView = itemView.findViewById(R.id.ivPicture)

        fun bind(news: News, clickListener: OnItemClickListener) {
            tvPublishedAdt.text = news.publishedAt
            tvAuthor.text = news.author
            tvTitle.text = news.title

            if (news.urlToImage.isNullOrEmpty()){
                ivPicture.setImageResource(R.drawable.newspaper)
            } else {
                Glide.with(itemView).load(news.urlToImage).into(ivPicture)
            }
            btnLink.setOnClickListener {
                clickListener.onItemClick(news)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //attach to root: no te sale nada
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position], clickListener)
    }

    interface OnItemClickListener {
        fun onItemClick(news: News)
    }
}