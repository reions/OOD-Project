package com.example.kauplus.study

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

class PostDetailFragment : Fragment() {

    private var binding: FragmentPostDetailBinding? = null
    private val viewModel: CommunityViewModel by activityViewModels()
    private lateinit var postId: String

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideMoreAndShowBack(true)
        (activity as MainActivity).hideLogoAndShowTitle(true)
        (activity as MainActivity).binding.navText.text = "글화면"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postId = arguments?.getString("postId") ?: ""
        viewModel.selectPost(postId)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)



        val bodytextAdapter = BodytextRVAdapter(emptyList()) { selectedBodytext ->
            viewModel.setSelectedBodytext(selectedBodytext)
        }

        binding?.rvBodytext?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = bodytextAdapter
        }

        viewModel.selectedBodytext.observe(viewLifecycleOwner) { bodytext ->
            bodytextAdapter.updateBodytexts(listOf(bodytext))
        }

        // Bodytext 데이터 반영
        /*viewModel.selectedBodytext.observe(viewLifecycleOwner) { bodytext ->
            bodytext?.let {
                binding?.txtInpostTitle?.text = it.in_post_title
                binding?.txtBodyText?.text = it.body_text
                binding?.txtTimeToMeet?.text = it.time_to_meet
                binding?.txtGroupspace?.text = it.group_space
            }
        }*/

        // 댓글 RecyclerView 설정
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
                val newComment = Comment(commentText, "11/17 12:30", "현재 사용자")
                viewModel.addComment(postId, newComment)
                binding?.etCommentInput?.text?.clear() // 입력 필드 초기화
                Toast.makeText(context, "댓글이 추가되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "댓글을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
