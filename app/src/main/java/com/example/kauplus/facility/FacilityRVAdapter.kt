package com.example.kauplus.facility

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.databinding.ItemMeetingBinding
import com.example.kauplus.viewmodel.ReservationViewModel

class FacilityRVAdapter(
    private var reservation: MutableList<Reservation>,
    private val reservationViewModel: ReservationViewModel
) : RecyclerView.Adapter<FacilityRVAdapter.NewsRVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsRVViewHolder {
        return NewsRVViewHolder(
            ItemMeetingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsRVViewHolder, position: Int) {
        val currentReservation = reservation[position]
        holder.title.text = currentReservation.roomName
        holder.time.text = "${currentReservation.time}:00 ~ ${currentReservation.time + 1}:00" // Int 값을 문자열로 변환하여 표시
        holder.place.text = currentReservation.purpose

        holder.delete.setOnClickListener {
            val reservationToDelete = reservation[position]
            reservationViewModel.deleteReservation(reservationToDelete)
        }
    }

    override fun getItemCount(): Int = reservation.size

    inner class NewsRVViewHolder(private val binding: ItemMeetingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.meetingTitle
        val time = binding.time
        val place = binding.place
        val delete = binding.delete
    }

    fun updateReservations(newReservations: List<Reservation>) {
        reservation.clear()
        reservation.addAll(newReservations)
        notifyDataSetChanged()
    }
}