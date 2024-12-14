package com.example.kauplus.ui.meeting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.kauplus.databinding.BottomSheetMeetingDetailBinding
import com.example.kauplus.viewmodel.MeetingViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MeetingDetailBottomSheet : BottomSheetDialogFragment() {

    val viewModel: MeetingViewModel by activityViewModels()
    private var binding: BottomSheetMeetingDetailBinding?=null
    private lateinit var meetingId: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetMeetingDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchMeeting(meetingId)

        viewModel.selectedMeeting.observe(viewLifecycleOwner) { meeting ->
            meeting?.let {
                val title=it.title
                val time=it.time
                val place=it.place
                val toDo=it.toDo

                binding?.title?.text = it.title
                binding?.time?.text = "\u2022 ${it.time}"
                binding?.place?.text = "\u2022 ${it.place}"

                Log.d("이미지", it.img1.toString())
                Log.d("이미지", it.img2.toString())
                Log.d("이미지", it.img3.toString())

                Glide.with(this)
                    .load(it.img1)
                    .placeholder(com.example.kauplus.R.drawable.img_bg)
                    .into(binding?.imgMeeting1?:throw Exception("imgMeeting is null"))

                Glide.with(this)
                    .load(it.img2)
                    .placeholder(com.example.kauplus.R.drawable.img_bg)
                    .into(binding?.imgMeeting2?:throw Exception("imgMeeting is null"))

                Glide.with(this)
                    .load(it.img3)
                    .placeholder(com.example.kauplus.R.drawable.img_bg)
                    .into(binding?.imgMeeting3?:throw Exception("imgMeeting is null"))

                val toDoAdapter = ToDoRVAdapter(requireContext(), it.toDo)
                binding?.listToDo?.adapter = toDoAdapter

                binding?.btnShare?.setOnClickListener {
                    val intent = Intent(Intent.ACTION_SEND_MULTIPLE)
                    intent.type = "text/plain"

                    val titleBold = "**$title**"
                    val toDoList = toDo.joinToString("\n") { "- $it" }
                    val content = """
                        팀원이 회의 일정을 공유했어요!
                        
                        $titleBold
                        
                        • 날짜: $time
                        • 장소: $place
                        
                        해야 할 일:
                        $toDoList
                    """.trimIndent()

                    intent.putExtra(Intent.EXTRA_TEXT, content)
                    val chooserTitle = "친구에게 공유하기"
                    startActivity(Intent.createChooser(intent, chooserTitle))
                }
                binding?.btnClose?.setOnClickListener {
                    viewModel.closeMeeting(meetingId)
                    dismiss()
                }
            }
        }

    }

    companion object {
        fun newInstance(meetingId: String): MeetingDetailBottomSheet {
            val fragment = MeetingDetailBottomSheet()
            fragment.meetingId = meetingId
            return fragment
        }
    }

}
