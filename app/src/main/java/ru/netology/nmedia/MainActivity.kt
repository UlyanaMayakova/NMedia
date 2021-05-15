package ru.netology.nmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
                val intent = Intent().apply{
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.postText)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(intent, getString(R.string.share))
                startActivity(shareIntent)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }
        })

        binding.rvPostsFeed.adapter = adapter
        viewModel.data.observe(this) { post ->
            adapter.submitList(post)
        }
        viewModel.edited.observe(this) { post ->
            if (post.id == 0) {
                return@observe
            }

            binding.group.visibility = View.VISIBLE
            binding.editing.text = post.postText

            with(binding.input) {
                requestFocus()
                setText(post.postText)
            }
        }

        binding.saveBtn.setOnClickListener {
            with(binding.input) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(this@MainActivity,
                            R.string.empty_input, Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                viewModel.changeContent(text.toString())
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

        binding.cancelBtn.setOnClickListener {
            with(binding.input) {
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
            binding.group.visibility = View.GONE
        }

    }
}