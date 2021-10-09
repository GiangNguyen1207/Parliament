package com.example.android.parliament.screens.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.parliament.data.Comment
import com.example.android.parliament.databinding.CommentRowBinding

//Giang Nguyen - 2.10.2021

class AllCommentsAdapter :
    ListAdapter<Comment, AllCommentsAdapter.ViewHolder>(CommentsListDiffCallBack()) {

    class ViewHolder private constructor(private val binding: CommentRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //bind the comment to enable using data variable in file xml.
        fun bind(comment: Comment) {
            binding.comment = comment
        }

        //inflate the layout for each comment row
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CommentRowBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = getItem(position)
        holder.bind(comment)
    }
}

//a callback class to observe the difference between the list of two adapters.
class CommentsListDiffCallBack : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }

}