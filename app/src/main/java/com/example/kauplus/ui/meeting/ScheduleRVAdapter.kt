package com.example.kauplus.ui.meeting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.R
import com.example.kauplus.databinding.ItemMeetingBinding

class ScheduleRVAdapter (itemList: MutableList<Meeting>): RecyclerView.Adapter<ScheduleRVAdapter.NewsRVViewHolder>() {

    var meetingList: MutableList<Meeting> =itemList
        set(value){
            field=value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScheduleRVAdapter.NewsRVViewHolder {
        return NewsRVViewHolder(
            ItemMeetingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    interface MyItemClickListener{
        fun onItemClick(position: Int)

    }
    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        myItemClickListener=itemClickListener
    }

    inner class NewsRVViewHolder(private val binding: ItemMeetingBinding) :
        RecyclerView.ViewHolder(binding.root){
        val title=binding.meetingTitle
        val time=binding.time
        val place=binding.place
        val delete=binding.delete
    }

    override fun onBindViewHolder(holder: ScheduleRVAdapter.NewsRVViewHolder, position: Int) {
        holder.title.text=meetingList[position].title
        holder.time.text=holder.itemView.context.getString(R.string.to_do_item, meetingList[position].time)
        holder.place.text=holder.itemView.context.getString(R.string.to_do_item, meetingList[position].place)
        holder.itemView.setOnClickListener {
            myItemClickListener.onItemClick(position)
        }
        holder.delete.setOnClickListener {
            deleteItem(position)
        }
    }

    private fun deleteItem(position: Int) {
        meetingList.removeAt(position)          // Remove the item from the list
        notifyItemRemoved(position)             // Notify the adapter of item removal
        notifyItemRangeChanged(position, meetingList.size) // Update positions of remaining items
    }
    override fun getItemViewType(position: Int): Int =position

    override fun getItemCount(): Int =meetingList.size


}