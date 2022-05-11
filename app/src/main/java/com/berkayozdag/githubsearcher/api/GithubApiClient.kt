package com.berkayozdag.githubsearcher.api

import com.berkayozdag.githubsearcher.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubApiClient {

    fun getApiService(): GithubApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(GithubApiService::class.java)
    }

}