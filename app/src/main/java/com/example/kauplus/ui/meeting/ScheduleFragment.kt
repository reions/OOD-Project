package com.example.kauplus.ui.meeting

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kauplus.MainActivity
import com.example.kauplus.R
import com.example.kauplus.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {

    private var itemList: ArrayList<Meeting> = ArrayList()
    private lateinit var binding:FragmentScheduleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).hideMoreAndShowBack(false)
        (activity as MainActivity).hideLogoAndShowTitle(true)
        (activity as MainActivity).binding.navText.text="시설 신청"

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentScheduleBinding.inflate(inflater,container,false)

        //대부분의 작업 수행
        itemList= arrayListOf(
            Meeting("프로젝트 X 8주차 회의","14:00~15:00","C1 스터디룸"),
            Meeting("산학 2주차 회의","13:00~14:00","전자관 413호")
        )

        val scheduleRVAdapter = ScheduleRVAdapter(itemList)
        val recyclerView = binding.rvMeeting
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = scheduleRVAdapter

        scheduleRVAdapter.setMyItemClickListener(object :ScheduleRVAdapter.MyItemClickListener {

            override fun onItemClick(position: Int) {
                val bottomSheet = MeetingDetailBottomSheet()
                bottomSheet.show(parentFragmentManager, MeetingDetailBottomSheet().tag)
            }

        })
        binding.textReservedMeeting.setOnClickListener {
            binding.textReservedMeeting.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.textClosedMeeting.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
            binding.btnWriteMeeting.visibility=View.VISIBLE
        }
        binding.textClosedMeeting.setOnClickListener {
            binding.textReservedMeeting.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
            binding.textClosedMeeting.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.btnWriteMeeting.visibility=View.GONE
        }
        return binding.root

    }

}