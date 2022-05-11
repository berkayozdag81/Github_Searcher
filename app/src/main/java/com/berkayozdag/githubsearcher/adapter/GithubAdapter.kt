package com.berkayozdag.githubsearcher.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkayozdag.githubsearcher.R
import com.berkayozdag.githubsearcher.models.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_users.view.*

class GithubAdapter : RecyclerView.Adapter<GithubAdapter.GithubViewHolder>() {
    inner class GithubViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldUser: User, newUser: User): Boolean {
            return oldUser.url == newUser.url
        }

        override fun areContentsTheSame(oldUser: User, newUser: User): Boolean {
            return oldUser == newUser
        }
    }

    val differ = AsyncListDiffer(this, differCallback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        return GithubViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_users,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((User) -> Unit)? = null

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val user = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(user.avatarUrl).into(ivUserImage)
            tvTitle.text = user.login
            tvDescription.text = user.type
            tvSource.text = user.url

            setOnClickListener {
                onItemClickListener?.let { it(user) }
            }
        }
    }

    fun setOnItemClickListener(listener: (User) -> Unit) {
        onItemClickListener = listener
    }
}