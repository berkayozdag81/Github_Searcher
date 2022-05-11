package com.berkayozdag.githubsearcher.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.berkayozdag.githubsearcher.repository.GithubRepository

class UserViewModelProviderFactory(private val githubRepository: GithubRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GithubViewModel::class.java)) {
            return GithubViewModel(githubRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}