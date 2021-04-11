package ru.netology.nmedia

import java.math.BigDecimal
import java.math.RoundingMode

data class Post(
        val id: Int,
        val groupName: String,
        val date: String,
        val postText: String,
        val likesCount: Int,
        val sharesCount: Int,
        val viewsCount: Int,
        val likedByMe: Boolean = false
) {
    fun numbersStyle(number: Int): String {

        return when (number) {
                in 0..999 -> number.toString()
                in 1_000..1_099 -> (number / 1_000).toString() + "K"
                in 10_000..999_999 -> (number / 1_000).toString() + "K"
                in 1_100..9_999 -> (BigDecimal(number.toDouble() / 1_000)
                        .setScale(1, RoundingMode.DOWN)).toString() + "K"
                in 1_000_000..1_099_999 -> (number / 1_000_000).toString() + "M"
            else -> (BigDecimal(number.toDouble() / 1_000_000)
                    .setScale(1, RoundingMode.DOWN)).toString() + "M"
        }
    }
}