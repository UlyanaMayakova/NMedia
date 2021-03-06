package ru.netology.nmedia

import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.PostBinding

class PostViewHolder(
        private val binding: PostBinding,
        private val onLikeListener: OnLikeListener,
        private val onShareListener: OnShareListener) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            groupName.text = post.groupName
            date.text = post.date
            postText.text = post.postText
            icon.setImageResource(R.drawable.ic_launcher_netology_foreground)
            likesCount.text = post.numbersStyle(post.likesCount)
            sharesCount.text = post.numbersStyle(post.sharesCount)
            viewsCount.text = post.numbersStyle(post.viewsCount)

            like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked else R.drawable.ic_like
            )

            like.setOnClickListener {
                onLikeListener(post)
            }

            share.setOnClickListener{
                onShareListener(post)
            }
        }
    }
}