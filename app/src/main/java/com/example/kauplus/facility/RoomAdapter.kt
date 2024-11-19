package com.example.kauplus.facility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.R

class RoomAdapter(
    private val roomList: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomName: TextView = itemView.findViewById(R.id.textView19)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_res_time, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val roomText = roomList[position]
        holder.roomName.text = roomText

        holder.itemView.setOnClickListener {
            onItemClick(roomText)
        }
    }

    override fun getItemCount() = roomList.size
}