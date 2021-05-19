package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post

class PostRepositoryInMemoryImpl : PostRepository {

    private var posts = listOf(
            Post(
                    id = 2,
                    author = "Нетология. Университет интернет-профессий",
                    date = "24 марта 16:00",
                    postText = "Очень большой и очень интересный текст про что-то безумно интересное и невообразимо крутое",
                    likesCount = 15,
                    sharesCount = 1,
                    viewsCount = 1_200,
                    likedByMe = false),

            Post(
                    id = 1,
                    author = "Нетология. Университет интернет-профессий",
                    date = "26 марта 17:30",
                    postText = "А это уже другой мега мощный текст, даже круче, чем предыдущий",
                    likesCount = 25,
                    sharesCount = 3,
                    viewsCount = 13_400,
                    likedByMe = false
            )
    )

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Int) {
        posts = posts.map {
            if (it.id != id) it else it.copy(likedByMe = !it.likedByMe,
                    likesCount = if (it.likedByMe) it.likesCount - 1 else it.likesCount + 1)
        }
        data.value = posts
    }

    override fun shareById(id: Int) {
        posts = posts.map {
            if (it.id != id) it else it.copy(sharesCount = it.sharesCount + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Int) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        posts = if (post.id == 0) {
            listOf(
                post.copy(
                    id = posts.first().id + 1,
                    author = "Me",
                    date = "Now"
                )
            ) + posts
        } else {
            posts
        }
        data.value = posts

        posts = posts.map {
            if (it.id != post.id) it else it.copy(postText = post.postText)
        }
        data.value = posts
    }
}