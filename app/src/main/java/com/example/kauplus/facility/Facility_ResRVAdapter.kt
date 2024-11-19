package com.example.kauplus.ui.meeting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.R
import com.example.kauplus.facility.ReservationTime

class Facility_ResRVAdapter(
    private val timeList: ArrayList<ReservationTime>,
    private val onTimeSelected: (ReservationTime) -> Unit
) : RecyclerView.Adapter<Facility_ResRVAdapter.FacilityViewHolder>() {

    private val selectedPositions = mutableSetOf<Int>() // 중복 선택 허용

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_res_time, parent, false)
        return FacilityViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val currentItem = timeList[position]
        holder.textView.text = currentItem.time

        // 선택 상태에 따른 배경색 변경
        if (selectedPositions.contains(position)) {
            holder.textView.setBackgroundResource(R.drawable.blue_stroke_rectangle)
            holder.textView.setTextColor(ContextCompat.getColor(holder.textView.context, R.color.white))
        } else {
            holder.textView.setBackgroundResource(R.drawable.gray_stroke_rectangle)
            holder.textView.setTextColor(ContextCompat.getColor(holder.textView.context, R.color.black))
        }

        // 항목 클릭 시 선택 상태 업데이트 및 콜백 전달
        holder.itemView.setOnClickListener {
            if (selectedPositions.contains(position)) {
                selectedPositions.remove(position) // 이미 선택된 경우 해제
            } else {
                selectedPositions.add(position) // 새로운 선택 추가
                onTimeSelected(currentItem) // 선택한 시간 전달
            }
            notifyItemChanged(position) // 선택된 항목만 업데이트
        }
    }

    override fun getItemCount(): Int = timeList.size

    inner class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView19)
    }
}