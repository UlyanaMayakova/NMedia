package ru.netology.nmedia

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.databinding.NewPostActivityBinding

class NewPostActivity : AppCompatActivity() {

    companion object {
        const val POST_KEY = "post"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = NewPostActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newPostText.requestFocus()

        binding.saveFab.setOnClickListener {
            val text = binding.newPostText.text?.toString()
            if (text.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED)
            } else {
                val intent = Intent().putExtra(
                    POST_KEY, Post(
                        id = 0,
                        author = "Me",
                        date = "Today",
                        postText = text,
                        likesCount = 1,
                        sharesCount = 0,
                        viewsCount = 3,
                        likedByMe = false
                    )
                )
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}
