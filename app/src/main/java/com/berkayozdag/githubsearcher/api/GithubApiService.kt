package com.berkayozdag.githubsearcher.api

import com.berkayozdag.githubsearcher.models.RepoResponse
import com.berkayozdag.githubsearcher.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {
    @GET("users")
    suspend fun getUsersList(
        @Query("q")
        query: String,
    ): Response<UserResponse>


    @GET("repositories")
    suspend fun getRepoList(
        @Query("q")
        query: String,
    ): Response<RepoResponse>

}