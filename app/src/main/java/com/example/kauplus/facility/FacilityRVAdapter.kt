package com.example.kauplus.ui.meeting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.databinding.ItemMeetingBinding
import com.example.kauplus.facility.Reservation
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
        holder.time.text = currentReservation.time
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

