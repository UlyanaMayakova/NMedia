package ru.netology.nmedia

import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.PostBinding

class PostViewHolder(
    private val binding: PostBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            date.text = post.date
            postText.text = post.postText
            icon.setImageResource(R.drawable.ic_launcher_netology_foreground)
            like.text = NumberStyle.change(post.likesCount)
            share.text = NumberStyle.change(post.sharesCount)
            views.text = NumberStyle.change(post.viewsCount)
            like.isChecked = post.likedByMe
            videoGroup.visibility = View.GONE

            if (post.videoUrl != null) {
                videoGroup.visibility = View.VISIBLE
                playBtn.setOnClickListener {
                    onInteractionListener.onVideo(post)
                }
                video.setOnClickListener {
                    onInteractionListener.onVideo(post)
                }
            }

            like.setOnClickListener {
                onInteractionListener.onLike(post)
            }

            share.setOnClickListener {
                onInteractionListener.onShare(post)
            }

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }
}