package com.example.kauplus.facility

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kauplus.databinding.FragmentFacilityBottomSeatBinding
import com.example.kauplus.viewmodel.ReservationViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class facility_bottom_seatFragment : BottomSheetDialogFragment() {

    private val selectedTimes = mutableSetOf<Int>() // 선택된 시간 목록
    private var purpose: String? = null
    private var binding: FragmentFacilityBottomSeatBinding? = null

    private val reservationViewModel: ReservationViewModel by activityViewModels()
    private lateinit var facilityResRVAdapter: Facility_ResRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFacilityBottomSeatBinding.inflate(inflater, container, false)

        // 방 이름 가져오기
        val roomText = arguments?.getString("roomText") ?: "C1 스터디룸"
        binding?.txtName?.text = roomText

        // 예약 가능한 시간 목록
        val timeList = arrayListOf(
            ReservationTime(9),
            ReservationTime(10),
            ReservationTime(11),
            ReservationTime(12),
            ReservationTime(13),
            ReservationTime(14),
            ReservationTime(15),
            ReservationTime(16),
            ReservationTime(17),
            ReservationTime(18),
            ReservationTime(19),
            ReservationTime(20),
            ReservationTime(21),
            ReservationTime(23)
        )

        // ViewModel에서 방별 예약된 시간 가져오기
        val reservedTimes: MutableSet<Int> =
            reservationViewModel.getReservedTimesForRoom(roomText).toMutableSet()

        // 어댑터 생성 및 초기화
        facilityResRVAdapter = Facility_ResRVAdapter(timeList, reservedTimes) { selected ->
            if (selectedTimes.contains(selected.time)) {
                selectedTimes.remove(selected.time) // 선택 취소
            } else {
                selectedTimes.add(selected.time) // 선택 추가
            }
            android.util.Log.d("FacilityBottomSeat", "Selected times: $selectedTimes")
        }

        binding?.recTime?.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = facilityResRVAdapter
        }

        // 사용 목적 입력
        binding?.txtPurpose?.setOnClickListener {
            val input = EditText(requireContext()).apply { hint = "사용 목적을 입력해 주세요." }
            AlertDialog.Builder(requireContext())
                .setTitle("사용 목적 입력")
                .setView(input)
                .setPositiveButton("확인") { dialog, _ ->
                    purpose = input.text.toString()
                    binding?.txtPurpose?.text = purpose
                    dialog.dismiss()
                }
                .setNeutralButton("취소") { dialog, _ -> dialog.cancel() }
                .show()
        }

        // 확인 버튼 클릭 시 예약 확정창 표시
        binding?.btmRes?.setOnClickListener {
            if (selectedTimes.isNotEmpty()) {
                val confirmResBottomSheet = confirmResFragment().apply {
                    arguments = Bundle().apply {
                        putString("roomText", roomText)
                        putString(
                            "timeTextList",
                            selectedTimes.joinToString(", ") { "${it}:00 ~ ${it + 1}:00" }
                        )
                        putString("purposeText", purpose)
                    }
                }

                // 예약 확정 화면 표시
                confirmResBottomSheet.show(parentFragmentManager, "confirmResFragment")

                // 현재 바텀시트 닫기
                dismiss()
            }
        }

        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}




