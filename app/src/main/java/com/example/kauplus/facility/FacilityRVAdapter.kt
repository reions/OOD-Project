package com.example.kauplus.facility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.R

class FacilityRVAdapter(private val reservationList: ArrayList<Reservation>) :
    RecyclerView.Adapter<FacilityRVAdapter.FacilityViewHolder>() {

    // ViewHolder 클래스 정의
    inner class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomNameTextView: TextView = itemView.findViewById(R.id.textView35)
        val timeTextView: TextView = itemView.findViewById(R.id.textView32)
        val purposeTextView: TextView = itemView.findViewById(R.id.textView33)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        // fragment_frag_cencle.xml 레이아웃을 인플레이트하여 ViewHolder 생성
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_frag_cencle, parent, false)
        return FacilityViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        // 현재 위치의 데이터를 ViewHolder에 바인딩
        val reservation = reservationList[position]
        holder.roomNameTextView.text = reservation.roomName
        holder.timeTextView.text = reservation.time
        holder.purposeTextView.text = reservation.purpose
    }

    override fun getItemCount(): Int {
        // 데이터의 총 개수 반환
        return reservationList.size
    }
}
