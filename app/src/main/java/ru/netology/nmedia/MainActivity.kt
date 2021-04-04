package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->

            with(binding) {
                icon.setImageResource(R.drawable.ic_launcher_netology_foreground)
                groupName.text = post.groupName
                date.text = post.date
                postText.text = post.postText
                likesCount.text = post.numbersStyle(post.likesCount)
                sharesCount.text = post.numbersStyle(post.sharesCount)
                viewsCount.text = post.numbersStyle(post.viewsCount)
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked else R.drawable.ic_like
                )
            }

            binding.like.setOnClickListener {
                binding.likesCount.text = post.numbersStyle(post.likesCount)
                viewModel.like()
            }

            binding.share.setOnClickListener {
                binding.sharesCount.text = post.numbersStyle(post.sharesCount)
                viewModel.share()
            }
        }

    }
}