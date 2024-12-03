package com.example.kauplus.study

import PostViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kauplus.MainActivity
import com.example.kauplus.databinding.FragmentPostDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostDetailFragment : Fragment() {

    private var binding: FragmentPostDetailBinding? = null
    private val viewModel: PostViewModel by activityViewModels()
    private lateinit var postId: String

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideMoreAndShowBack(true)
        (activity as MainActivity).hideLogoAndShowTitle(true)
        (activity as MainActivity).binding.navText.text = "작성글"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postId = arguments?.getString("postId") ?: ""
        viewModel.fetchBodytext(postId)
        viewModel.fetchComments(postId)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)


        val postId = arguments?.getString("postId") ?: ""
        viewModel.fetchBodytext(postId) // 데이터 로드

        val bodytextAdapter = BodytextRVAdapter(emptyList()) { bodytext ->
        }
        binding?.rvBodytext?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = bodytextAdapter
        }

        viewModel.selectedBodytext.observe(viewLifecycleOwner) { bodytext ->
            bodytext?.let {
                // 단일 객체를 리스트로 변환하여 Adapter에 전달
                bodytextAdapter.updateBodytexts(listOf(it))
            }
        }


        val commentAdapter = CommentRVAdapter(emptyList())
        binding?.rvComment?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = commentAdapter
        }

        viewModel.comments.observe(viewLifecycleOwner) { comments ->
            commentAdapter.updateComments(comments)
        }

        // 댓글 전송 버튼 클릭 이벤트
        binding?.btnSend?.setOnClickListener {
            val commentText = binding?.etCommentInput?.text.toString()
            if (commentText.isNotBlank()) {
                val currentTime = SimpleDateFormat("MM/dd HH:mm", Locale.getDefault()).format(Date())
                val newComment = Comment(commentText, currentTime, "사용자")

                viewModel.addComment(postId, newComment)
                binding?.etCommentInput?.text?.clear() // 입력 필드 초기화
                Toast.makeText(context, "댓글이 추가되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "댓글을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        binding?.txtJoin?.setOnClickListener {
            val studyAcceptFragment = StudyAcceptFragment().apply {
                arguments = Bundle().apply {
                    putString("postId", postId)
                }
            }
            studyAcceptFragment.show(parentFragmentManager, "StudyAcceptFragment")
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
