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

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _userInfo = MutableLiveData<List<User>>()
    val userInfo: LiveData<List<User>> get() = _userInfo

    fun getUsersList(){
        viewModelScope.launch {
            var response=githubRepository.getUsersList()
            if(response.isSuccessful){
                response.body()?.items.let {
                    _users.postValue(it)
                }
            }
            else {
                Log.d("Response", "Error occurred in getUsersList()")
            }
        }
    }

    fun getUserInfo(username:String){
        viewModelScope.launch {
            var response=githubRepository.getUserInfo(username)
            if(response.isSuccessful){
                response.body()?.items.let {
                    //var userInfo=it?.get(0)
                    _userInfo.postValue(it)
                }
            }
            else {
                Log.d("Response", "Error occurred in getUserInfo()")
            }
        }
    }
}