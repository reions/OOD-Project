package com.example.kauplus.facility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kauplus.databinding.FragmentFragCencleBinding

class fragCencle : Fragment() {
    private var reservation: ArrayList<Reservation> = ArrayList()
    lateinit var binding: FragmentFragCencleBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFragCencleBinding.inflate(inflater)
        reservation = arrayListOf(
            Reservation("C1 스터디룸", "14:00 ~ 15:00","프로젝트 회의"),
            Reservation("C2 스터디룸", "17:00 ~ 18:00","산학 회의")
        )

        val facilityRVAdapter = FacilityRVAdapter(reservation)
        val recyclerView = binding.recClass
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = facilityRVAdapter
        return (binding.root)
    }

}