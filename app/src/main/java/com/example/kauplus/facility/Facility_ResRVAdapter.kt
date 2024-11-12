package com.example.kauplus.ui.meeting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.R
import com.example.kauplus.facility.ReservationTime

class Facility_ResRVAdapter(private val timeList: ArrayList<ReservationTime>) : RecyclerView.Adapter<Facility_ResRVAdapter.FacilityViewHolder>() {

    private val selectedPositions = mutableSetOf<Int>() // 선택된 항목 위치들을 저장하는 Set

    var TimeList: MutableList<ReservationTime> = timeList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_res_time, parent, false)
        return FacilityViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val currentItem = TimeList[position]
        holder.textView.text = currentItem.time

        holder.itemView.setOnClickListener {
            if (selectedPositions.contains(position)) {
                selectedPositions.remove(position) // 이미 선택된 경우 선택 해제
            } else {
                selectedPositions.add(position) // 새로운 항목을 선택
            }
            notifyItemChanged(position) // 선택된 항목만 갱신
        }
        // 선택 여부에 따라 TextView의 배경색과 텍스트 색상을 설정
        if (selectedPositions.contains(position)) {
            holder.textView.setBackgroundResource(R.drawable.blue_stroke_rectangle) // 선택된 경우 파란색 배경
            holder.textView.setTextColor(ContextCompat.getColor(holder.textView.context, R.color.white)) // 텍스트 하얀색
        } else {
            holder.textView.setBackgroundResource(R.drawable.gray_stroke_rectangle) // 기본 회색 테두리 배경
            holder.textView.setTextColor(ContextCompat.getColor(holder.textView.context, R.color.black)) // 텍스트 검정색
        }

    }

    override fun getItemCount(): Int = TimeList.size

    inner class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView19)
    }
}
