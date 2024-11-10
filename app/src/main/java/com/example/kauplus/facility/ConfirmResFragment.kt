package com.example.kauplus.facility

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kauplus.R
import com.example.kauplus.databinding.FragmentConfirmResBinding
import com.example.kauplus.databinding.FragmentFragCencleBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class confirmResFragment :  BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentConfirmResBinding.inflate(inflater)
        //val roomText = arguments?.getString("roomText") ?: "C1 스터디룸"
        //binding.textView27.text = roomText
        return binding.root
    }

}