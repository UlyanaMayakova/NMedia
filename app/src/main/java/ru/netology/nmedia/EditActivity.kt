package ru.netology.nmedia

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    companion object {
        const val EDITED_POST = "edit"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = intent.getParcelableExtra<Post>(EDITED_POST)

        binding.editPostText.setText(post?.postText)

        binding.saveFab.setOnClickListener {
            val text = binding.editPostText.text?.toString()
            if (text.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED)
            } else {
                val intent = Intent().putExtra(
                    EDITED_POST, post?.copy(postText = text)
                )
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
}