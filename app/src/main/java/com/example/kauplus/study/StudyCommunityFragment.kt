package com.example.kauplus.study

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kauplus.MainActivity
import com.example.kauplus.R
import com.example.kauplus.databinding.FragmentStudyCommunityBinding
import com.example.kauplus.study.Posts


// 여기서 다음으로 화면전환하는 건 다음의 코드를 참고
//      scheduleRVAdapter.setMyItemClickListener(object :ScheduleRVAdapter.MyItemClickListener {
//            override fun onItemClick(position: Int) {
//                val bottomSheet = MeetingDetailBottomSheet()
//                bottomSheet.show(parentFragmentManager, MeetingDetailBottomSheet().tag)
//            }
//            binding.btnWriteMeeting.setOnClickListener {
//    (activity as MainActivity).addFragment(WriteMeetingFragment())

class StudyCommunityFragment : Fragment() {
    private lateinit var binding : FragmentStudyCommunityBinding
    private var itemlist : ArrayList<Posts> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity).hideMoreAndShowBack(false)
        (activity as MainActivity).hideLogoAndShowTitle(false)
        (activity as MainActivity).binding.navText.text="홈화면"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyCommunityBinding.inflate(inflater, container, false)
        itemlist = arrayListOf(
            Posts("알고리즘 스터디 멤버 구합니다.", "14 : 00 ~ 15 : 00", "C1 스터디룸", "3/5"),
            Posts("러닝 크루 모집", "14 : 00 ~ 15 : 00", "운동장", "2/5")
        )
        val postRVAdapter = PostRVAdapter(itemlist)
        binding.rvPosts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvPosts.adapter = postRVAdapter
        return inflater.inflate(R.layout.fragment_study_community, container, false)
    }


}