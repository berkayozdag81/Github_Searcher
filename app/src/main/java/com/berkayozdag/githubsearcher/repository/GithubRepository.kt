package com.berkayozdag.githubsearcher.repository

import com.berkayozdag.githubsearcher.api.GithubApiClient

class GithubRepository(
) {
    suspend fun getUsersList(query: String) = GithubApiClient.getApiService().getUsersList(query)
    suspend fun getRepoList(query: String) = GithubApiClient.getApiService().getRepoList(query)
}