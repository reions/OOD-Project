package com.example.kauplus.study

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.databinding.ItemBodytextBinding

class BodytextRVAdapter(private var bodytextList: List<Bodytext>) : RecyclerView.Adapter<BodytextRVAdapter.BodytextViewHolder>() {

    inner class BodytextViewHolder(private val binding: ItemBodytextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.txtInpostTitle
        val bodyText = binding.txtBodyText
        val time = binding.txtTimeToMeet
        val space = binding.txtGroupspace
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BodytextViewHolder {
        val binding = ItemBodytextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BodytextViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BodytextViewHolder, position: Int) {
        val currentBodyText = bodytextList[position]
        holder.title.text = currentBodyText.in_post_title
        holder.bodyText.text = currentBodyText.body_text
        holder.time.text = currentBodyText.time_to_meet
        holder.space.text = currentBodyText.gruop_space
    }

    override fun getItemCount(): Int {
        return bodytextList.size
    }
}
