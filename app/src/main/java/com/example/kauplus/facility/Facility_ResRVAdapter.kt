package com.example.kauplus.facility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.R

class Facility_ResRVAdapter(
    private val timeList: ArrayList<ReservationTime>,
    private var reservedTimes: MutableSet<Int>, // 예약된 시간 목록
    private val onTimeSelected: (ReservationTime) -> Unit
) : RecyclerView.Adapter<Facility_ResRVAdapter.FacilityViewHolder>() {

    private val selectedPositions = mutableSetOf<Int>() // 선택된 시간 목록

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_res_time, parent, false)
        return FacilityViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val currentItem = timeList[position]
        val context = holder.textView.context

        android.util.Log.d("FacilityAdapter", "Binding time: ${currentItem.time}, Reserved: ${reservedTimes.contains(currentItem.time)}")

        if (reservedTimes.contains(currentItem.time)) {
            // 예약된 시간의 UI
            holder.textView.setBackgroundResource(R.drawable.reserved_time_background)
            holder.textView.setTextColor(ContextCompat.getColor(context, R.color.white))
            holder.itemView.isEnabled = false // 클릭 비활성화
        } else {
            // 예약 가능 시간 UI
            holder.textView.setBackgroundResource(
                if (selectedPositions.contains(position)) {
                    R.drawable.blue_stroke_rectangle // 선택된 경우
                } else {
                    R.drawable.gray_stroke_rectangle // 선택되지 않은 경우
                }
            )
            holder.textView.setTextColor(
                if (selectedPositions.contains(position)) {
                    ContextCompat.getColor(context, R.color.white)
                } else {
                    ContextCompat.getColor(context, R.color.black)
                }
            )

            // 클릭 이벤트 설정
            holder.itemView.isEnabled = true
            holder.itemView.setOnClickListener {
                if (selectedPositions.contains(position)) {
                    selectedPositions.remove(position)
                } else {
                    selectedPositions.add(position)
                    onTimeSelected(currentItem)
                }
                notifyItemChanged(position)
            }
        }

        // 시간 텍스트 설정
        holder.textView.text = "${currentItem.time}:00 ~ ${currentItem.time + 1}:00"
    }

    override fun getItemCount(): Int = timeList.size

    inner class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView19)
    }


}



