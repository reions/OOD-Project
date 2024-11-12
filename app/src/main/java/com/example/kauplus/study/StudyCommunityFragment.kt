package com.example.kauplus.study

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kauplus.MainActivity
import com.example.kauplus.databinding.FragmentStudyCommunityBinding
import com.example.kauplus.study.PostRVAdapter.MyItemClickListener
import com.example.kauplus.ui.meeting.ScheduleRVAdapter


class StudyCommunityFragment : Fragment() {

    private var binding : FragmentStudyCommunityBinding? = null
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

        binding?.let {
            it.rvPosts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            it.rvPosts.adapter = postRVAdapter
        }

        postRVAdapter.setMyItemClickListener(object :PostRVAdapter.MyItemClickListener{
            override fun onItemClick(position: Int) {
                (activity as MainActivity).addFragment(PostDetailFragment())
            }
        })

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}