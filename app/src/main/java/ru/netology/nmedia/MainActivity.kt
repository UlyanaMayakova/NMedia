package ru.netology.nmedia

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_NEW = 1
        const val REQUEST_CODE_EDIT = 2
    }

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                val intent = Intent(this@MainActivity, EditActivity::class.java)
                intent.putExtra(EditActivity.EDITED_POST, post)
                startActivityForResult(intent, REQUEST_CODE_EDIT)
            }

            override fun onVideo(post: Post) {
                val url = viewModel.getVideoUrl(post)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
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
        }

//        val newPostLauncher = registerForActivityResult(NewPostResultContract()) {result ->
//            result ?: return@registerForActivityResult
//            viewModel.changeContent(result)
//            viewModel.save()
//        }

        binding.addFab.setOnClickListener {
            val intent = Intent(this, NewPostActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_NEW)
//            newPostLauncher.launch()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_NEW && resultCode == RESULT_OK && data != null) {
            val post = data.getParcelableExtra<Post>(NewPostActivity.POST_KEY) ?: return
            viewModel.edit(post)
            viewModel.save()
        } else if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK && data != null) {
            val post = data.getParcelableExtra<Post>(EditActivity.EDITED_POST) ?: return
            viewModel.edit(post)
            viewModel.save()
        }
    }
}