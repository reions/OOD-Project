package com.example.kauplus.facility

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kauplus.databinding.FragmentFacilityBottomSeatBinding
import com.example.kauplus.ui.meeting.Facility_ResRVAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class facility_bottom_seatFragment : BottomSheetDialogFragment() {

    private var timeList: ArrayList<ReservationTime> = ArrayList()
    private  var binding: FragmentFacilityBottomSeatBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFacilityBottomSeatBinding.inflate(inflater, container, false)

        timeList = arrayListOf(
            ReservationTime("09:00~10:00"),
            ReservationTime("10:00~11:00"),
            ReservationTime("11:00~12:00"),
            ReservationTime("12:00~13:00"),
            ReservationTime("13:00~14:00"),
            ReservationTime("14:00~15:00"),
            ReservationTime("15:00~16:00"),
            ReservationTime("16:00~17:00"),
            ReservationTime("17:00~18:00"),
            ReservationTime("18:00~19:00"),
            ReservationTime("19:00~20:00"),
            ReservationTime("20:00~21:00"),
            ReservationTime("21:00~22:00"),
            ReservationTime("23:00~24:00")
        )


        val facilityResRVAdapter = Facility_ResRVAdapter(timeList)
        binding?.recTime?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding?.recTime?.adapter = facilityResRVAdapter





        binding?.txtPurpose?.setOnClickListener{
            val input = EditText(requireContext()).apply {
                hint = "사용 목적을 입력해 주세요."
            }
            AlertDialog.Builder(requireContext())
                .setTitle("사용 목적 입력")
                .setView(input)
                .setPositiveButton("확인"){dialog,_ ->
                    val enteredText = input.text.toString()
                    binding?.txtPurpose?.text = enteredText
                    dialog.dismiss()
                }
                .setNeutralButton("취소"){dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }
        val roomText = arguments?.getString("roomText") ?: "C1 스터디룸"
        binding?.txtName?.text = roomText

        binding?.btmRes?.setOnClickListener {
            val confirmResBottomsheets = confirmResFragment()
            confirmResBottomsheets.show(parentFragmentManager,"confirmResFragment")
        }

        return binding.root
    }


    private fun applyFadeAnimation(view: View, fromAlpha: Float, toAlpha: Float, duration: Long) {
        val animator = ObjectAnimator.ofFloat(view, View.ALPHA, fromAlpha, toAlpha)
        animator.duration = duration
        animator.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }



}

