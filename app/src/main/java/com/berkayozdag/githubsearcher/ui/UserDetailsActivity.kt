package com.berkayozdag.githubsearcher.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.berkayozdag.githubsearcher.databinding.ActivityDetailsBinding
import com.berkayozdag.githubsearcher.models.User

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val people = intent.getSerializableExtra("user") as? User

        binding.nameText.text = people?.login.toString()
        binding.imageUrlText.text = people?.avatarUrl.toString()
        binding.userUrlTextView.text = people?.url.toString()

    }
}