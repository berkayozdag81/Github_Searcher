package com.berkayozdag.githubsearcher.api

import com.berkayozdag.githubsearcher.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {
    @GET("users")
    suspend fun getUsersList(
        @Query("q")
        username: String="username",
    ): Response<UserResponse>

    @GET("users?")
    suspend fun getUserInfo(
        @Query("q")
        username: String,
    ): Response<UserResponse>
}