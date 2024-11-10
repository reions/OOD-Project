package com.example.kauplus.study

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.databinding.ItemPostsBinding

class PostRVAdapter(itemList: MutableList<Posts>) : RecyclerView.Adapter<PostRVAdapter.PostRVViewHolder>() {

    var postList: MutableList<Posts> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostRVAdapter.PostRVViewHolder {
        return PostRVViewHolder(
            ItemPostsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class PostRVViewHolder(private val binding: ItemPostsBinding) :
        RecyclerView.ViewHolder(binding.root){
        val title=binding.txtPostingTitle
        val time=binding.txtTime
        val place=binding.txtSpace
        val participant = binding.txtParticipant
    }

    override fun onBindViewHolder(holder: PostRVAdapter.PostRVViewHolder, position: Int) {
        holder.title.text = postList[position].posting_title
        holder.time.text = postList[position].time
        holder.place.text = postList[position].space
        holder.participant.text = postList[position].participant

    }

    override fun getItemCount() = postList.size
}