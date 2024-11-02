package com.example.kauplus.ui.meeting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kauplus.MainActivity
import com.example.kauplus.R
import com.example.kauplus.databinding.FragmentStudyBinding
import com.example.kauplus.databinding.FragmentWriteMeetingBinding

class WriteMeetingFragment : Fragment() {
    private lateinit var binding: FragmentWriteMeetingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).hideMoreAndShowBack(true)
        (activity as MainActivity).hideLogoAndShowTitle(true)
        (activity as MainActivity).binding.navText.text="회의 작성"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWriteMeetingBinding.inflate(inflater, container, false)

        val writeToDoRVAdapter = WriteToDoRVAdapter(arrayListOf())
        val recyclerView = binding.rvWriteToDo
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = writeToDoRVAdapter

        binding.btnWriteToDo.setOnClickListener {
            writeToDoRVAdapter.editList.add("")
            writeToDoRVAdapter.notifyDataSetChanged()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}