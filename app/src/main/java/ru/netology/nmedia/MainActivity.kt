package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ImageViewCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val icon: ImageView = findViewById(R.id.icon)
        val groupName: TextView = findViewById(R.id.groupName)
        val date: TextView = findViewById(R.id.date)
        val postText: TextView = findViewById(R.id.postText)
        val likesCount: TextView = findViewById(R.id.likesCount)
        val sharesCount: TextView = findViewById(R.id.sharesCount)
        val viewsCount: TextView = findViewById(R.id.viewsCount)

        icon.setImageResource(R.drawable.ic_launcher_netology_foreground)
        groupName.text = "Нетология. Университет интернет-профессий"
        date.text = "24 марта 16:00"
        postText.text = "Очень большой и очень интересный текст про что-то безумно интересное и невообразимо крутое"
        likesCount.text = "4"
        sharesCount.text = "5"
        viewsCount.text = "200"

    }
}