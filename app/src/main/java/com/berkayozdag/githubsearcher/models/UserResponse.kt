package com.berkayozdag.githubsearcher.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("total_count") var totalCount: Int? = null,
    @SerializedName("incomplete_results") var incompleteResults: Boolean? = null,
    @SerializedName("items") var items: List<User> = listOf()
)
