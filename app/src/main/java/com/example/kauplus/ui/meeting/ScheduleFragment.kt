package com.example.kauplus.ui.meeting

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kauplus.MainActivity
import com.example.kauplus.R
import com.example.kauplus.databinding.FragmentScheduleBinding
import com.example.kauplus.viewmodel.MeetingViewModel

class ScheduleFragment : Fragment() {

    private var itemList: ArrayList<Meeting> = ArrayList()
    private lateinit var binding: FragmentScheduleBinding
    val viewModel: MeetingViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).binding.navText.text = "회의 일정"
        (activity as MainActivity).hideMoreAndShowBack(false)
        (activity as MainActivity).hideLogoAndShowTitle(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.rvMeeting
        val scheduleRVAdapter = ScheduleRVAdapter(viewModel.itemSchedule)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = scheduleRVAdapter
        /*
        itemList = arrayListOf(
            Meeting("프로젝트 X 8주차 회의", "14:00~15:00", "C1 스터디룸"),
            Meeting("산학 2주차 회의", "13:00~14:00", "전자관 413호")
        )
        */
        viewModel.itemSchedule.observe(viewLifecycleOwner) {
           scheduleRVAdapter.notifyDataSetChanged()
        }

        scheduleRVAdapter.setMyItemClickListener(object : ScheduleRVAdapter.MyItemClickListener {

            override fun onItemClick(meetingId: String?) {
                if (meetingId!=null){
                    val bottomSheet = MeetingDetailBottomSheet.newInstance(meetingId)
                    bottomSheet.show(parentFragmentManager, MeetingDetailBottomSheet().tag)
                }
            }

            override fun onDeleteMeeting(meetingId: String?) {
                viewModel.deleteMeeting(meetingId)
            }

        })

        binding.textReservedMeeting.setOnClickListener {
            binding.textReservedMeeting.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
            binding.textClosedMeeting.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.gray
                )
            )
            binding.btnWriteMeeting.visibility = View.VISIBLE
        }
        binding.textClosedMeeting.setOnClickListener {
            binding.textReservedMeeting.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.gray
                )
            )
            binding.textClosedMeeting.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
            binding.btnWriteMeeting.visibility = View.GONE
        }

        binding.btnWriteMeeting.setOnClickListener {
            (activity as MainActivity).addFragment(WriteMeetingFragment())
        }
    }

}