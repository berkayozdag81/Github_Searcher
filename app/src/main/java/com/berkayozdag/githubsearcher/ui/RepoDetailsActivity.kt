package com.berkayozdag.githubsearcher.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.berkayozdag.githubsearcher.databinding.ActivityRepoDetailsBinding
import com.berkayozdag.githubsearcher.models.Repos

class RepoDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRepoDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repos = intent.getSerializableExtra("repo") as? Repos
    }
}