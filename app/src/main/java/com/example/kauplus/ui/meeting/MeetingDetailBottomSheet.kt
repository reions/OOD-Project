package com.example.kauplus.ui.meeting

import android.R
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.kauplus.databinding.BottomSheetMeetingDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.combine


class MeetingDetailBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = BottomSheetMeetingDetailBinding.inflate(inflater, container, false)
        val toDoList= arrayListOf<String>(
            "피피티 디자인 정하기",
            "최종 발표자 정하기",
            "저번주 개발 진행 상황 브리핑"
        )

        val toDoAdapter = ToDoRVAdapter(requireContext(), toDoList)
        binding.listToDo.adapter = toDoAdapter

        return binding.root

    }

}