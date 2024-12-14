package com.example.kauplus.ui.meeting

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kauplus.MainActivity
import com.example.kauplus.R
import com.example.kauplus.databinding.FragmentWriteMeetingBinding
import com.example.kauplus.viewmodel.MeetingViewModel

class WriteMeetingFragment : Fragment() {
    private var binding: FragmentWriteMeetingBinding?=null
    private val viewModel: MeetingViewModel by activityViewModels()
    private var imageUris = mutableListOf<Uri?>(null, null, null)
    private var selectedImage = ""

    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openGallery()
            }
        }
    private var pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                context?.let { context ->
                    val targetImageView = when (selectedImage) {
                        "img1" -> {
                            imageUris[0] = uri
                            binding?.imgMeeting1
                        }
                        "img2" -> {
                            imageUris[1] = uri
                            binding?.imgMeeting2
                        }
                        "img3" -> {
                            imageUris[2] = uri
                            binding?.imgMeeting3
                        }
                        else -> null
                    }
                    targetImageView?.let {
                        Glide.with(context)
                            .load(uri)
                            .placeholder(R.drawable.img_bg)
                            .error(R.drawable.img_bg)
                            .into(it)
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).hideMoreAndShowBack(true)
        (activity as MainActivity).hideLogoAndShowTitle(true)
        (activity as MainActivity).binding.navText.text = "회의 작성"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteMeetingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val writeToDoRVAdapter = WriteToDoRVAdapter(arrayListOf())
        binding?.rvWriteToDo?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.rvWriteToDo?.adapter = writeToDoRVAdapter

        binding?.btnWriteToDo?.setOnClickListener {
            writeToDoRVAdapter.editList.add("")
            writeToDoRVAdapter.notifyDataSetChanged()
        }

        binding?.imgMeeting1?.setOnClickListener {
            selectImage("img1")
        }

        binding?.imgMeeting2?.setOnClickListener {
            selectImage("img2")
        }

        binding?.imgMeeting3?.setOnClickListener {
            selectImage("img3")
        }

        binding?.btnWriteFinish?.setOnClickListener {
            val title = binding?.etTitle?.text.toString()
            val time = binding?.etTime?.text.toString()
            val place = binding?.etPlace?.text.toString()
            val toDoList = writeToDoRVAdapter.editList.filter { it.isNotEmpty() }
            Log.d("이미지", imageUris.toString())
            viewModel.saveMeeting(title, time, place, toDoList, imageUris)
            (activity as MainActivity).onBackPressed()
        }

    }
    private fun selectImage(selectedImg:String) {
        selectedImage = selectedImg
        checkPermission()
    }

    private fun checkPermission() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        } else {
            requestPermissionLauncher.launch(permission)
        }
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        pickImageLauncher.launch(galleryIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).binding.navText.text="회의 일정"
        (activity as MainActivity).hideMoreAndShowBack(false)
        (activity as MainActivity).hideLogoAndShowTitle(true)
        binding = null
    }
}