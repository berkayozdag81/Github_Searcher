package com.berkayozdag.githubsearcher.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkayozdag.githubsearcher.adapter.GithubAdapter
import com.berkayozdag.githubsearcher.databinding.ActivityMainBinding
import com.berkayozdag.githubsearcher.repository.GithubRepository
import com.berkayozdag.githubsearcher.util.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GithubViewModel
    private val githubAdapter = GithubAdapter(this)
    private val githubRepository=GithubRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this,UserViewModelProviderFactory(githubRepository))[GithubViewModel::class.java]
        setupRecyclerView()
        viewModel.getViewItems()
    }

    override fun onStart() {
        super.onStart()
        viewModel.items.observe(this){viewItems ->
            githubAdapter.differ.submitList(viewItems)
        }
        viewModel.isLoading.observe(this){isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        viewModel.getViewItems(editable.toString())
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvUsers.apply {
            adapter = githubAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}