package com.example.kauplus.facility

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.kauplus.MainActivity
import com.example.kauplus.databinding.FragmentConfirmResBinding
import com.example.kauplus.viewmodel.ReservationViewModel

class confirmResFragment : DialogFragment() {

    private var binding: FragmentConfirmResBinding? = null
    private val reservationViewModel: ReservationViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmResBinding.inflate(inflater, container, false)

        val roomText = arguments?.getString("roomText") ?: "C1 스터디룸"
        val timeText = arguments?.getString("timeText") ?: "시간 미선택"
        val purposeText = arguments?.getString("purposeText") ?: "" // 추가된 목적 가져오기
        binding?.textView27?.text = "$roomText $timeText"

        // 확정 버튼 클릭 시 예약을 ViewModel에 추가
        binding?.viewOk?.setOnClickListener {
            val reservation = Reservation(roomText, timeText, purposeText)
            reservationViewModel.addReservation(reservation)
            dismiss()
            (activity as? MainActivity)?.addFragment(facilityAppFragment()) // 돌아가기
        }

        binding?.viewCen?.setOnClickListener {
            dismiss()
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}


