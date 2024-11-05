package com.example.kauplus.facility

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.example.kauplus.R
import com.example.kauplus.databinding.BottomSheetMeetingDetailBinding
import com.example.kauplus.databinding.FragmentFacilityAppBinding
import com.example.kauplus.databinding.FragmentFacilityBottomSeatBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class facility_bottom_seatFragment : BottomSheetDialogFragment() {
    private var selectedImageView: View? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFacilityBottomSeatBinding.inflate(inflater, container, false)

        binding.txtPurpose.setOnClickListener{
            val input = EditText(requireContext()).apply {
                hint = "사용 목적을 입력해 주세요."
            }
            AlertDialog.Builder(requireContext())
                .setTitle("사용 목적 입력")
                .setView(input)
                .setPositiveButton("확인"){dialog,_ ->
                    val enteredText = input.text.toString()
                    binding.txtPurpose.text = enteredText
                    dialog.dismiss()
                }
                .setNeutralButton("취소"){dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }
        binding.bottomBtn1.setOnClickListener { ImageSelection(it as ImageView) }
        binding.bottomBtn2.setOnClickListener { ImageSelection(it as ImageView) }
        binding.bottomBtn3.setOnClickListener { ImageSelection(it as ImageView) }

        val roomText = arguments?.getString("roomText") ?: "C1 스터디룸"
        binding.txtName.text = roomText

        binding.btmRes.setOnClickListener {
            val confirmResBottomsheets = confirmResFragment()
            confirmResBottomsheets.show(parentFragmentManager,"confirmResFragment")
        }

        return binding.root

    }
    private fun ImageSelection(view: ImageView) {
        if (selectedImageView == view) {
            // 현재 이미지 선택 해제
            view.setImageResource(R.drawable.image_for)
            selectedImageView = null
        } else {
            // 이전 선택된 이미지를 원래 색으로 돌림
            selectedImageView?.setImageResource(R.drawable.image_for)
            // 새로운 이미지 선택하여 파란색으로 설정
            view.setImageResource(R.drawable.image_for_blue)
            selectedImageView = view
        }
    }

    private fun applyFadeAnimation(view: View, fromAlpha: Float, toAlpha: Float, duration: Long) {
        val animator = ObjectAnimator.ofFloat(view, View.ALPHA, fromAlpha, toAlpha)
        animator.duration = duration
        animator.start()
    }



}

private fun View?.setImageResource(white: Int) {

}
