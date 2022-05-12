package com.berkayozdag.githubsearcher.models

data class RepoResponse(
    val incomplete_results: Boolean,
    val items: List<Repos>,
    val total_count: Int
)