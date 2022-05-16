package com.berkayozdag.githubsearcher.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkayozdag.githubsearcher.databinding.ItemReposBinding
import com.berkayozdag.githubsearcher.databinding.ItemUsersBinding
import com.berkayozdag.githubsearcher.models.Repos
import com.berkayozdag.githubsearcher.models.User
import com.berkayozdag.githubsearcher.ui.GithubViewItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_users.view.*

class GithubAdapter(
    private val context: Context,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<GithubAdapter.GithubViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<GithubViewItem>() {
        override fun areItemsTheSame(oldItem: GithubViewItem, newItem: GithubViewItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GithubViewItem, newItem: GithubViewItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
       return when(viewType) {
            GithubViewItem.VIEW_TYPE_ONE -> View1ViewHolder(
                ItemUsersBinding.inflate(
                    LayoutInflater.from(context),parent,false
                )
            )
           GithubViewItem.VIEW_TYPE_TWO -> View2ViewHolder(
               ItemReposBinding.inflate(
                   LayoutInflater.from(context),parent,false
               )
            )
           else -> throw Throwable("hata")
        }
    }
    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return differ.currentList[position].type
    }

    override fun getItemCount(): Int = differ.currentList.size

    abstract inner class GithubViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        abstract fun bind(position: Int)
    }

    private inner class View1ViewHolder(private val binding: ItemUsersBinding) : GithubViewHolder(binding.root) {
        override fun bind(position: Int) {
            val recyclerViewModel = differ.currentList[position] as GithubViewItem.User
            val user = recyclerViewModel.data
            Glide.with(itemView).load(user.avatarUrl).into(binding.ivImage)
            binding.Title.text = user.login
            binding.tvDescription.text = user.type
            binding.tvSource.text = user.url
            binding.root.setOnClickListener {
                clickListener.onUserClicked(user)
            }
        }
    }

    private inner class View2ViewHolder(private val binding: ItemReposBinding) : GithubViewHolder(binding.root) {
        override fun bind(position: Int) {
            val recyclerViewModel = differ.currentList[position] as GithubViewItem.Repo
            val repo = recyclerViewModel.data
            Glide.with(itemView).load(repo.owner.avatar_url).into(binding.ivRepoImage)
            binding.tvRepoTitle.text = repo.full_name
            binding.tvRepoDescription.text = repo.name
            binding.tvRepoSource.text = repo.commits_url
            binding.root.setOnClickListener {
                clickListener.onRepoClicked(repo)
            }
        }
    }

    interface ClickListener{
        fun onUserClicked(user : User)
        fun onRepoClicked(repo: Repos)
    }
}