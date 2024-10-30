package com.example.kauplus.ui.meeting

import android.R
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.kauplus.databinding.BottomSheetMeetingDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.combine


class MeetingDetailBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = BottomSheetMeetingDetailBinding.inflate(inflater, container, false)

        return binding.root

    }


    private fun applyFadeAnimation(view: View, fromAlpha: Float, toAlpha: Float, duration: Long) {
        val animator = ObjectAnimator.ofFloat(view, View.ALPHA, fromAlpha, toAlpha)
        animator.duration = duration
        animator.start()
    }
}