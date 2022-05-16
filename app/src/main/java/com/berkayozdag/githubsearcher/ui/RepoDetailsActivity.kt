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

        binding.repoNameText.text = repos?.name.toString()
        binding.repoOwnerNameText.text = repos?.owner?.login.toString()
        binding.repoOwnerAvatarUrltxt.text = repos?.owner?.avatar_url.toString()
        binding.repoUrlTxt.text = repos?.url.toString()
        binding.repoLanguageTxt.text = repos?.language.toString()
        binding.repoBranchTxt.text = repos?.branches_url.toString()
    }
}