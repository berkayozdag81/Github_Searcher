package com.berkayozdag.githubsearcher.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkayozdag.githubsearcher.adapter.GithubAdapter
import com.berkayozdag.githubsearcher.databinding.ActivityMainBinding
import com.berkayozdag.githubsearcher.repository.GithubRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GithubViewModel
    private val githubAdapter = GithubAdapter()
    private val githubRepository=GithubRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this,UserViewModelProviderFactory(githubRepository))[GithubViewModel::class.java]
        setupRecyclerView()
        viewModel.getUsersList()
    }


    override fun onStart() {
        super.onStart()
        viewModel.users.observe(this){response ->
            Toast.makeText(this,response[0].login.toString(),Toast.LENGTH_LONG).show()
            githubAdapter.differ.submitList(response)
        }
    }

    private fun setupRecyclerView() {
        println("ben malım ${binding.rvUsers}" )
        binding.rvUsers.apply {
            adapter = githubAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}