package com.example.kauplus.study

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.databinding.ItemBodytextBinding

class BodytextRVAdapter(
    private var bodytextList: List<Bodytext> = emptyList(),
    private val onItemClicked: (Bodytext) -> Unit // 아이템 클릭 리스너
) : RecyclerView.Adapter<BodytextRVAdapter.BodytextViewHolder>() {

    // ViewHolder 클래스
    inner class BodytextViewHolder(private val binding: ItemBodytextBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // ViewHolder에서 데이터 바인딩 처리
        fun bind(bodytext: Bodytext) {
            binding.txtInpostTitle.text = bodytext.in_post_title
            binding.txtBodyText.text = bodytext.body_text
            binding.txtTimeToMeet.text = bodytext.time_to_meet
            binding.txtGroupspace.text = bodytext.group_space

            // 아이템 클릭 이벤트 처리
            binding.root.setOnClickListener {
                onItemClicked(bodytext)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BodytextViewHolder {
        val binding = ItemBodytextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BodytextViewHolder(binding)
    }
    fun updateBodytexts(newBodytexts: List<Bodytext?>) {
        bodytextList = newBodytexts.filterNotNull() // null 값을 필터링
        notifyDataSetChanged() // RecyclerView를 갱신
    }

    override fun onBindViewHolder(holder: BodytextViewHolder, position: Int) {
        holder.bind(bodytextList[position]) // 데이터 바인딩 호출
    }

    override fun getItemCount(): Int = bodytextList.size
}
