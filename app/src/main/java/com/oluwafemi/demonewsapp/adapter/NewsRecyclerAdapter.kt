package com.oluwafemi.demonewsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oluwafemi.demonewsapp.databinding.NewsItemsBinding
import com.oluwafemi.demonewsapp.model.Article
import com.oluwafemi.demonewsapp.model.News

class NewsRecyclerAdapter : ListAdapter<Article, NewsRecyclerAdapter.NewsViewHolder>(DiffUtilCallback){
    companion object DiffUtilCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }
    }
    inner class NewsViewHolder(private var binding: NewsItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article){
            binding.news = article
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(NewsItemsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsProperty = getItem(position)
        holder.bind(newsProperty)
    }
}


