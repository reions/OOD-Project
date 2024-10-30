package com.example.kauplus.ui.meeting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kauplus.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding=FragmentScheduleBinding.inflate(inflater,container,false)
        return binding.root

        //대부분의 작업 수행
    }

}