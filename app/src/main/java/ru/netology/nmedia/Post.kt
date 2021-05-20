package ru.netology.nmedia

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
        val id: Int,
        val author: String,
        val date: String,
        val postText: String,
        val likesCount: Int,
        val sharesCount: Int,
        val viewsCount: Int,
        val likedByMe: Boolean = false,
        val videoUrl: String? = null
) : Parcelable