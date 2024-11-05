package com.example.kauplus.facility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.kauplus.MainActivity
import com.example.kauplus.R
import com.example.kauplus.databinding.FragmentFacilityAppBinding
import com.example.kauplus.ui.meeting.MeetingDetailBottomSheet


class facilityAppFragment : Fragment() {


    private  var binding: FragmentFacilityAppBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).hideMoreAndShowBack(false)
        (activity as MainActivity).hideLogoAndShowTitle(true)
        (activity as MainActivity).binding.navText.text=""

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFacilityAppBinding.inflate(inflater,container,false)

        binding?.resIm?.setOnClickListener {

        }
        binding?.resTxt?.setOnClickListener{
           
        }

        binding?.c1Btn?.setOnClickListener { onItemClick("C1 스터디룸") }
        binding?.c1Btn2?.setOnClickListener { onItemClick("C2 스터디룸") }
        binding?.c1Btn3?.setOnClickListener { onItemClick("C3 스터디룸") }
        binding?.btnA1?.setOnClickListener { onItemClick("스터디룸 1") }
        binding?.btnA2?.setOnClickListener { onItemClick("스터디룸 2") }
        binding?.btnE2?.setOnClickListener { onItemClick("전자관 201호") }
        binding?.btnE3?.setOnClickListener { onItemClick("전자관 202호") }
        binding?.btnE1?.setOnClickListener { onItemClick("전자관 413호") }
        binding?.btnS1?.setOnClickListener { onItemClick("농구장") }
        binding?.btnS2?.setOnClickListener { onItemClick("테니스장") }
        binding?.btnS3?.setOnClickListener { onItemClick("운동장") }




        return binding?.root
    }



    private fun onItemClick(roomText: String) {
        val bottomSheet = facility_bottom_seatFragment()
        val args = Bundle()
        args.putString("roomText", roomText)
        bottomSheet.arguments = args
        bottomSheet.show(parentFragmentManager, "facility_bottom_seatFragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

