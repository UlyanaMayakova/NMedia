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
        val adapter = PostAdapter {
            viewModel.likeById(it.id)
        }

        binding.rvPostsFeed.adapter = adapter
        viewModel.data.observe(this) { post ->
            adapter.submitList(post)

//            binding.like.setOnClickListener {
//                binding.likesCount.text = post.numbersStyle(post.likesCount)
//                viewModel.like()
//            }
//
//            binding.share.setOnClickListener {
//                binding.sharesCount.text = post.numbersStyle(post.sharesCount)
//                viewModel.share()
//            }
//        }

        }
    }
}