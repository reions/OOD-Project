package com.example.kauplus.study

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
// import com.example.kauplus.ARG_PARAM1
// import com.example.kauplus.ARG_PARAM2
import com.example.kauplus.MainActivity
import com.example.kauplus.R

class StudyCommunityFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).hideMoreAndShowBack(false)
        (activity as MainActivity).hideLogoAndShowTitle(false)
        (activity as MainActivity).binding.navText.text="홈화면"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_study_community, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudyCommunityFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudyCommunityFragment().apply {

            }
    }
}