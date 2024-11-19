package com.example.kauplus.facility

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

    private val selectedTimes = mutableSetOf<String>()
    private var purpose: String? = null
    private var binding: FragmentFacilityBottomSeatBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFacilityBottomSeatBinding.inflate(inflater, container, false)

        val timeList = arrayListOf(
            ReservationTime("09:00~10:00"),
            ReservationTime("10:00~11:00"),
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

        val facilityResRVAdapter = Facility_ResRVAdapter(timeList) { selected ->
            if (selectedTimes.contains(selected.time)) {
                selectedTimes.remove(selected.time)
            } else {
                selectedTimes.add(selected.time)
            }
        }

        binding?.recTime?.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = facilityResRVAdapter
        }

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

        val roomText = arguments?.getString("roomText") ?: "C1 스터디룸"
        binding?.txtName?.text = roomText

        binding?.btmRes?.setOnClickListener {
            selectedTimes.forEach { time ->
                val confirmResBottomSheet = confirmResFragment().apply {
                    arguments = Bundle().apply {
                        putString("roomText", roomText)
                        putString("timeText", time)
                        putString("purposeText", purpose)
                    }
                }
                confirmResBottomSheet.show(parentFragmentManager, "confirmResFragment")
            }
            dismiss()
        }

        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
