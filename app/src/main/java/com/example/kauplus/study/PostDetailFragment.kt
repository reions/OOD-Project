package com.example.kauplus.study

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kauplus.MainActivity
import com.example.kauplus.databinding.FragmentPostDetailBinding

// 댓글 추가하는 기능은 writePostFrangment에 interface 부분이랑 WriteMeetingFragment
class PostDetailFragment : Fragment() {
    private var binding : FragmentPostDetailBinding ?= null


    override fun onResume() {
        super.onResume()

        (activity as MainActivity).hideMoreAndShowBack(true)
        (activity as MainActivity).hideLogoAndShowTitle(false)
        (activity as MainActivity).binding.navText.text="글화면"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        val comments = listOf(
            Comment("멤버가 합이 잘 맞을 경우 정기 스터디 진행할 계획이 있습니다.", "10/22 18:05", "helloworld!08"),
            Comment("아직도 구하나요?", "10/22 18:12", "송동호")
        )

        val commentAdapter = CommentRVAdapter(comments)
        binding?.rvComment?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = commentAdapter
        }
        val bodytexts = listOf(
            Bodytext("알고리즘 스터디 멤버 구합니다", "내일 도서관 스터디룸에서 함께 알고리즘 공부할 동기 모집합니다. \n남녀 학년 상관 없으니 부담없이 참여해주시면 감사하겠습니다.",
                "\u00B7 14 : 00~15 : 00", "\u00B7 C1 스터디룸"),
            Bodytext("러닝 크루 모집", "같이 학교 운동장에서 러닝 하실 크루원들을 모집합니다. \n남녀, 학년 상관없고 잘뛰지 못해도 괜찮으니 많은 참여 바랍니다.",
                "\u00B7 14 : 00~15 : 00", "\u00B7 운동장")
        )

        val bodytextAdapter = BodytextRVAdapter(bodytexts)
        binding?.rvBodytext?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = bodytextAdapter
        }

        return binding?.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}