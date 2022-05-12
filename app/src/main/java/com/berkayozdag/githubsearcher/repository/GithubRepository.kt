package com.berkayozdag.githubsearcher.repository

import com.berkayozdag.githubsearcher.api.GithubApiClient

class GithubRepository(
) {
    suspend fun getUsersList() = GithubApiClient.getApiService().getUsersList()

    suspend fun getUserInfo(username:String) = GithubApiClient.getApiService().getUserInfo(username)

    suspend fun getRepoList() = GithubApiClient.getApiService().getRepoList()

}