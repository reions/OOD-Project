package com.example.kauplus.study

import PostViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.kauplus.R

class StudyAcceptFragment : DialogFragment() {
    private val viewModel: PostViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 팝업 스타일 설정
        setStyle(STYLE_NORMAL, R.style.CustomDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_study_accept, container, false)

        val postId = arguments?.getString("postId") ?: ""
        val post = viewModel.posts.value?.find { it.id == postId }

        // 취소 버튼 이벤트
        view.findViewById<View>(R.id.btn_cancle).setOnClickListener {
            dismiss() // 팝업 닫기
        }

        // 확정 버튼 이벤트
        view.findViewById<View>(R.id.btn_accept).setOnClickListener {
            post?.let {
                if (it.currentParticipants < it.maxParticipants) {
                    viewModel.updateParticipantCount(postId, it.currentParticipants + 1)
                    Toast.makeText(context, "그룹 참여가 확정되었습니다!", Toast.LENGTH_SHORT).show()
                    dismiss()
                } else {
                    Toast.makeText(context, "인원이 가득 찼습니다!", Toast.LENGTH_SHORT).show()
                }
            } ?: Toast.makeText(context, "게시글 정보를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
        }

        // X 버튼 이벤트
        view.findViewById<View>(R.id.btn_close).setOnClickListener {
            dismiss() // 팝업 닫기
        }

        return view
    }
}
