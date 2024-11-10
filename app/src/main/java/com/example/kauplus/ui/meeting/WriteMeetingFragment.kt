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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kauplus.MainActivity
import com.example.kauplus.R
import com.example.kauplus.databinding.FragmentStudyBinding
import com.example.kauplus.databinding.FragmentWriteMeetingBinding
import java.io.File

class WriteMeetingFragment : Fragment() {
    private lateinit var binding: FragmentWriteMeetingBinding
    private var imageUri: Uri? = null
    private var selectedImage = ""

    // 갤러리 open
    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openGallery()
            }
        }
    private var pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                imageUri = uri
                context?.let { context ->
                    val targetImageView = when (selectedImage) {
                        "img1" -> binding.imgMeeting1
                        "img2" -> binding.imgMeeting2
                        "img3" -> binding.imgMeeting3
                        else -> null
                    }
                    targetImageView?.let {
                        Glide.with(context)
                            .load(imageUri)
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
        // 기타 초기화 코드
        (activity as MainActivity).hideMoreAndShowBack(true)
        (activity as MainActivity).hideLogoAndShowTitle(true)
        (activity as MainActivity).binding.navText.text = "회의 작성"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWriteMeetingBinding.inflate(inflater, container, false)

        val writeToDoRVAdapter = WriteToDoRVAdapter(arrayListOf())
        binding.rvWriteToDo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvWriteToDo.adapter = writeToDoRVAdapter

        binding.btnWriteToDo.setOnClickListener {
            writeToDoRVAdapter.editList.add("")
            writeToDoRVAdapter.notifyDataSetChanged()
        }

        binding.imgMeeting1.setOnClickListener {
            selectedImage = "img1"
            checkPermission()
        }

        binding.imgMeeting2.setOnClickListener {
            selectedImage = "img2"
            checkPermission()
        }

        binding.imgMeeting3.setOnClickListener {
            selectedImage = "img3"
            checkPermission()
        }

        return binding.root
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
    }
}