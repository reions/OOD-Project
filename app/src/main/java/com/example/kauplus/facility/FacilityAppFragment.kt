package com.example.kauplus.facility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.MainActivity
import com.example.kauplus.databinding.FragmentFacilityAppBinding


class facilityAppFragment : Fragment() {


    private  var binding: FragmentFacilityAppBinding? = null
    override fun onResume() {
        super.onResume()
        // MainActivity UI 설정
        (activity as MainActivity).hideMoreAndShowBack(false)
        (activity as MainActivity).hideLogoAndShowTitle(true)
        (activity as MainActivity).binding.navText.text = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFacilityAppBinding.inflate(inflater,container,false)


        val studyRooms = listOf("C1 스터디룸", "C2 스터디룸", "C3 스터디룸")
        val electronicsRooms = listOf("전자관 201호", "전자관 202호", "전자관 413호")
        val sportsFacilities = listOf("농구장", "테니스장", "축구장")
        val aerospaceCenter = listOf("스터디룸1", "스터디룸2")
        setupRecyclerView(binding?.rec1, studyRooms)
        setupRecyclerView(binding?.rec2, electronicsRooms)
        setupRecyclerView(binding?.rec3, sportsFacilities)
        setupRecyclerView(binding?.rec4, aerospaceCenter)


        binding?.resTxt?.setOnClickListener {
            (activity as MainActivity).addFragment(fragCencle())
        }
        binding?.resIm?.setOnClickListener {
            (activity as MainActivity).addFragment(fragCencle())
        }


        return binding?.root
    }
    private fun setupRecyclerView(recyclerView: RecyclerView?, roomList: List<String>) {
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(),  LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.adapter = RoomAdapter(roomList) { roomText ->
            onItemClick(roomText)
        }
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
