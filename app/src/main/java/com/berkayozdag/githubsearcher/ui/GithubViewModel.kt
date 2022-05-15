package com.berkayozdag.githubsearcher.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkayozdag.githubsearcher.models.User
import com.berkayozdag.githubsearcher.repository.GithubRepository
import kotlinx.coroutines.launch

class GithubViewModel(val githubRepository: GithubRepository) : ViewModel() {

    private val _items = MutableLiveData<List<GithubViewItem>>()
    val items : LiveData<List<GithubViewItem>> get() = _items

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> get() = _isLoading

    fun getViewItems(query : String? = null){
        viewModelScope.launch {
            _isLoading.postValue(true)
            val userResponse = githubRepository.getUsersList(query ?: "username")
            val repoResponse = githubRepository.getRepoList(query ?: "reponame")
            _isLoading.postValue(false)

            if(userResponse.isSuccessful && repoResponse.isSuccessful){
                val users = userResponse.body()?.items.orEmpty().map {
                    GithubViewItem.User(it)
                }
                val repos = repoResponse.body()?.items.orEmpty().map {repos ->
                    GithubViewItem.Repo(repos)
                }
                _items.postValue(users + repos)
            }
            else{
                println(userResponse.message())
                println(repoResponse.message())
            }
        }
    }
}