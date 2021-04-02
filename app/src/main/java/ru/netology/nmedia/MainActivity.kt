package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
                id = 1,
                groupName = "Нетология. Университет интернет-профессий",
                date = "24 марта 16:00",
                postText = "Очень большой и очень интересный текст про что-то безумно интересное и невообразимо крутое",
                likesCount = 9_221,
                sharesCount = 1_100,
                viewsCount = 1_200,
                likedByMe = false
        )

        with(binding) {
            icon.setImageResource(R.drawable.ic_launcher_netology_foreground)
            groupName.text = post.groupName
            date.text = post.date
            postText.text = post.postText
            likesCount.text = post.numbersStyle(post.likesCount)
            sharesCount.text = post.numbersStyle(post.sharesCount)
            viewsCount.text = post.numbersStyle(post.viewsCount)

            like.setOnClickListener {
                post.likedByMe = !post.likedByMe
                if (post.likedByMe) {
                    like.setImageResource(R.drawable.ic_liked)
                    likesCount.text = post.numbersStyle(++post.likesCount)
                } else {
                    like.setImageResource(R.drawable.ic_like)
                    likesCount.text = post.numbersStyle(--post.likesCount)
                }
            }

            share.setOnClickListener{
                    sharesCount.text = post.numbersStyle(++post.sharesCount)
            }
        }

    }
}