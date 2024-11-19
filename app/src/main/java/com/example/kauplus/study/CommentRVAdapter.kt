package com.example.kauplus.study

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.databinding.ItemCommentBinding

class CommentRVAdapter(private var commentList: List<Comment>) : RecyclerView.Adapter<CommentRVAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val comment = binding.txtComment
        val commentTime = binding.txtCommentTime
        val commentWriter = binding.txtCommentWriter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val currentComment = commentList[position]
        holder.comment.text = currentComment.comment
        holder.commentTime.text = currentComment.comment_time
        holder.commentWriter.text = currentComment.comment_writer
    }

    fun updateComments(newComments: List<Comment>) {
        commentList = newComments
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return commentList.size
    }
}
