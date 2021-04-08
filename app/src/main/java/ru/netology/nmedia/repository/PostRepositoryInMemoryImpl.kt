package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post

class PostRepositoryInMemoryImpl : PostRepository {

    private var post = Post(
        id = 1,
        groupName = "Нетология. Университет интернет-профессий",
        date = "24 марта 16:00",
        postText = "Очень большой и очень интересный текст про что-то безумно интересное и невообразимо крутое",
        likesCount = 15,
        sharesCount = 1,
        viewsCount = 1_200,
        likedByMe = false
    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data
    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe)

        post = if (post.likedByMe) {
            post.copy(likesCount = (post.likesCount + 1))
        } else {
            post.copy(likesCount = (post.likesCount - 1))
        }

        data.value = post
    }

    override fun share() {
        post = post.copy(sharesCount = (post.sharesCount + 1))
        data.value = post
    }
}