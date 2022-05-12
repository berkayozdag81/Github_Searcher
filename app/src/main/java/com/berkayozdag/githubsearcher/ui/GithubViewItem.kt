package com.berkayozdag.githubsearcher.ui

import com.berkayozdag.githubsearcher.models.Repos
import com.berkayozdag.githubsearcher.models.User

sealed class GithubViewItem(val type : Int) {

    data class User (val data : com.berkayozdag.githubsearcher.models.User) : GithubViewItem(VIEW_TYPE_ONE)

    data class Repo (val data : Repos) : GithubViewItem(VIEW_TYPE_TWO)

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

}