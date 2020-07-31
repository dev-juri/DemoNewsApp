package com.oluwafemi.demonewsapp.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.oluwafemi.demonewsapp.adapter.NewsRecyclerAdapter
import com.oluwafemi.demonewsapp.databinding.FragmentHeadlineBinding

class HeadlineFragment : Fragment() {

    private val viewModel: HeadlineViewModel by lazy {
        ViewModelProvider(this).get(HeadlineViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHeadlineBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.newsRecycler.adapter = NewsRecyclerAdapter()

        return binding.root
    }


}