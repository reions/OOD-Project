package com.example.kauplus.ui.meeting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.R
import com.example.kauplus.databinding.ItemMeetingBinding
import com.example.kauplus.viewmodel.MeetingViewModel

class ScheduleRVAdapter (private val meetingList: LiveData<ArrayList<Meeting>>): RecyclerView.Adapter<ScheduleRVAdapter.NewsRVViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick(meetingId: String?)
        fun onDeleteMeeting(meetingId: String?)

    }
    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        myItemClickListener=itemClickListener
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
    inner class NewsRVViewHolder(private val binding: ItemMeetingBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(meeting: Meeting?) {
            meeting?.let {
                binding.meetingTitle.text = it.title
                binding.time.text = it.time
                binding.place.text = it.place
            }
            binding.root.setOnClickListener {
                myItemClickListener.onItemClick(meeting?.id)
            }
            binding.delete.setOnClickListener {
                myItemClickListener.onDeleteMeeting(meeting?.id)
            }
        }
    }

    override fun onBindViewHolder(holder: ScheduleRVAdapter.NewsRVViewHolder, position: Int) {
        holder.bind(meetingList.value?.getOrNull(position))
    }
    override fun getItemViewType(position: Int): Int =position

    override fun getItemCount(): Int =meetingList.value?.size?:0


}