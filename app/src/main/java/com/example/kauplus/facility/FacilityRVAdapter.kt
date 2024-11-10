package com.example.kauplus.ui.meeting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.R
import com.example.kauplus.databinding.ItemMeetingBinding
import com.example.kauplus.facility.Reservation

class  FacilityRVAdapter(reservation: ArrayList<Reservation>): RecyclerView.Adapter<FacilityRVAdapter.NewsRVViewHolder>() {

    var reservation: MutableList<Reservation> = reservation
        set(value){
            field=value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FacilityRVAdapter.NewsRVViewHolder {
        return NewsRVViewHolder(
            ItemMeetingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsRVViewHolder, position: Int) {
        holder.title.text=reservation[position].roomName
        holder.time.text=holder.itemView.context.getString(R.string.to_do_item, reservation[position].time)
        holder.place.text=holder.itemView.context.getString(R.string.to_do_item, reservation[position].purpose)
        holder.itemView.setOnClickListener {
            myItemClickListener.onItemClick(position)
        }
        holder.delete.setOnClickListener {
            deleteItem(position)
        }
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


    private fun deleteItem(position: Int) {
        reservation.removeAt(position)          // Remove the item from the list
        notifyItemRemoved(position)             // Notify the adapter of item removal
        notifyItemRangeChanged(position, reservation.size) // Update positions of remaining items
    }
    override fun getItemViewType(position: Int): Int =position

    override fun getItemCount(): Int =reservation.size


}