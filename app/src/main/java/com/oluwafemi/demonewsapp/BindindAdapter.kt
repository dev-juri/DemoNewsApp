package com.oluwafemi.demonewsapp

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.oluwafemi.demonewsapp.adapter.NewsRecyclerAdapter
import com.oluwafemi.demonewsapp.model.Article

@BindingAdapter("newsData")
fun bindRecyclerView(recyclerView: RecyclerView, data : List<Article>?){
    val adapter = recyclerView.adapter as NewsRecyclerAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)

    }
}