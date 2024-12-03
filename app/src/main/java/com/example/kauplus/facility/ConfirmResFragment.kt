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
        val timeTextList = arguments?.getString("timeTextList") ?: ""
        val purposeText = arguments?.getString("purposeText") ?: ""

        // 시간 리스트를 예약 확인 화면에 표시
        binding?.textView27?.text = "$roomText $timeTextList"

        // 확정 버튼 클릭 시 예약 데이터를 ViewModel에 추가
        binding?.viewOk?.setOnClickListener {
            timeTextList.split(", ").forEach { timeRange ->
                val reservation = Reservation(
                    roomName = roomText,
                    time = extractStartTime(timeRange),
                    purpose = purposeText
                )
                reservationViewModel.addReservation(reservation)
            }

            // 바텀시트 닫기
            dismissBottomSheet()

            // 예약 완료 후 Fragment 전환
            dismiss()
            (activity as? MainActivity)?.addFragment(facilityAppFragment()) // 돌아가기
        }

        // 취소 버튼 클릭 시 팝업 닫기
        binding?.viewCen?.setOnClickListener {
            dismiss()
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    // 시간대에서 시작 시간을 추출하는 헬퍼 함수
    private fun extractStartTime(timeRange: String): Int {
        return timeRange.split(":")[0].toInt()
    }

    // 바텀시트(Fragment) 닫기
    private fun dismissBottomSheet() {
        val bottomSheet = parentFragmentManager.findFragmentByTag("facilityBottomSheet")
        if (bottomSheet is facility_bottom_seatFragment) {
            bottomSheet.dismiss()
        }
    }
}




