package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.icon.setImageResource(R.drawable.ic_launcher_netology_foreground)
        binding.groupName.text = "Нетология. Университет интернет-профессий"
        binding.date.text = "24 марта 16:00"
        binding.postText.text = "Очень большой и очень интересный текст про что-то безумно интересное и невообразимо крутое"
        binding.likesCount.text = "4"
        binding.sharesCount.text = "5"
        binding.viewsCount.text = "200"

    }
}