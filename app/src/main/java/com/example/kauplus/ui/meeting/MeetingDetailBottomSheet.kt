package com.example.kauplus.ui.meeting

import android.R
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.kauplus.databinding.BottomSheetMeetingDetailBinding
import com.example.kauplus.databinding.FragmentWriteMeetingBinding
import com.example.kauplus.viewmodel.MeetingViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.combine


class MeetingDetailBottomSheet : BottomSheetDialogFragment() {

    val viewModel: MeetingViewModel by activityViewModels()
    private lateinit var binding: BottomSheetMeetingDetailBinding
    private lateinit var meetingId: String // 필요에 따라 Meeting ID를 전달받거나 초기화
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetMeetingDetailBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toDoList= arrayListOf<String>(
            "피피티 디자인 정하기",
            "최종 발표자 정하기",
            "저번주 개발 진행 상황 브리핑"
        )
        // ViewModel에서 데이터 가져오기
        viewModel.fetchMeeting(meetingId)

        // Meeting 데이터 관찰
        viewModel.selectedMeeting.observe(viewLifecycleOwner) { meeting ->
            meeting?.let {
                binding.title.text = it.title
                binding.time.text = it.time
                binding.place.text = it.place

                // 이미지 설정
                Glide.with(this)
                    .load(it.img1)
                    .placeholder(com.example.kauplus.R.drawable.img_bg)
                    .into(binding.imgMeeting1)

                Glide.with(this)
                    .load(it.img2)
                    .placeholder(com.example.kauplus.R.drawable.img_bg)
                    .into(binding.imgMeeting2)

                Glide.with(this)
                    .load(it.img3)
                    .placeholder(com.example.kauplus.R.drawable.img_bg)
                    .into(binding.imgMeeting3)

                // 할 일 목록 설정
                val toDoAdapter = ToDoRVAdapter(requireContext(), it.toDo)
                binding.listToDo.adapter = toDoAdapter
            }
        }

        val toDoAdapter = ToDoRVAdapter(requireContext(), toDoList)
        binding.listToDo.adapter = toDoAdapter

    }
    companion object {
        fun newInstance(meetingId: String): MeetingDetailBottomSheet {
            val fragment = MeetingDetailBottomSheet()
            fragment.meetingId = meetingId
            return fragment
        }
    }

}
