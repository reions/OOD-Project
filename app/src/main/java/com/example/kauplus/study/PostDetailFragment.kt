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
import com.example.kauplus.facility.confirmResFragment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostDetailFragment : Fragment() {

    private var binding: FragmentPostDetailBinding? = null
    private val viewModel: CommunityViewModel by activityViewModels()
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
                val currentTime = SimpleDateFormat("MM/dd HH:mm", Locale.getDefault()).format(Date())
                val newComment = Comment(commentText, currentTime, "사용자")
                viewModel.addComment(postId, newComment)
                //viewModel.addFirebaseComment(newComment)
                //dismiss()
                //(activity as? MainActivity)?.addFragment(PostDetailFragment())
                binding?.etCommentInput?.text?.clear() // 입력 필드 초기화
                Toast.makeText(context, "댓글이 추가되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "댓글을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        /*binding?.txtJoin?.setOnClickListener {
            val studyAccept = StudyAcceptFragment()
            StudyAcceptFragment.show(parentFragmentManager, "studyAcceptFragment")
            dismiss()
        }*/


        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
