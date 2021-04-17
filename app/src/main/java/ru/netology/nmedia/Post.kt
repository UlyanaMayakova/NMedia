package ru.netology.nmedia

data class Post(
        val id: Int,
        val author: String,
        val date: String,
        val postText: String,
        val likesCount: Int,
        val sharesCount: Int,
        val viewsCount: Int,
        val likedByMe: Boolean = false
)