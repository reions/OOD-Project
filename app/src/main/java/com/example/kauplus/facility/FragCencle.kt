package com.example.kauplus.facility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kauplus.MainActivity
import com.example.kauplus.databinding.FragmentFragCencleBinding
import com.example.kauplus.ui.meeting.FacilityRVAdapter
import com.example.kauplus.viewmodel.ReservationViewModel

class fragCencle : Fragment() {
    private var binding: FragmentFragCencleBinding? = null
    private lateinit var facilityRVAdapter: FacilityRVAdapter
    private val reservationViewModel: ReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragCencleBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        facilityRVAdapter = FacilityRVAdapter(mutableListOf(), reservationViewModel)
        binding?.recClass?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = facilityRVAdapter
        }

        reservationViewModel.reservations.observe(viewLifecycleOwner) { reservations ->
            facilityRVAdapter.updateReservations(reservations)
        }
    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity).binding.navText.text = "내 예약"
        (activity as MainActivity).hideMoreAndShowBack(true)
        (activity as MainActivity).hideLogoAndShowTitle(true)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
