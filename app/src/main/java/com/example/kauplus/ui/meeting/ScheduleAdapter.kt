package com.example.kauplus.ui.meeting

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.databinding.ItemMeetingBinding

class ScheduleAdapter (itemList: List<Meeting>): RecyclerView.Adapter<ScheduleAdapter.NewsRVViewHolder>() {

    var meetingList: List<Meeting> =itemList
        set(value){
            field=value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScheduleAdapter.NewsRVViewHolder {
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

    inner class NewsRVViewHolder(val binding: ItemMeetingBinding) :
        RecyclerView.ViewHolder(binding.root){
        //val title=binding.newsTitle

    }

    override fun onBindViewHolder(holder: ScheduleAdapter.NewsRVViewHolder, position: Int) {
       /* holder.item.setOnClickListener{
            //onItemclick함수에 position만 넘김
            myItemClickListener.onItemClick(position)
        }*/
    }

    override fun getItemViewType(position: Int): Int =position

    override fun getItemCount(): Int =meetingList.size


}