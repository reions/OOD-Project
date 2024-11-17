package com.example.kauplus.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kauplus.repository.MeetingRepository
import com.example.kauplus.ui.meeting.Meeting
import kotlinx.coroutines.launch

class MeetingViewModel : ViewModel() {
    private val repository = MeetingRepository()

    private val _itemSchedule=MutableLiveData<ArrayList<Meeting>>()
    val itemSchedule : LiveData<ArrayList<Meeting>> = _itemSchedule

    private val _selectedMeeting = MutableLiveData<Meeting>()
    val selectedMeeting: LiveData<Meeting> = _selectedMeeting

    init {
        repository.observeMeetings(_itemSchedule)
    }

    fun fetchMeeting(meetingId: String) {
        viewModelScope.launch {
            val meeting = repository.getMeetingById(meetingId)
            _selectedMeeting.value = meeting?:throw NullPointerException("meeting is null")
        }
    }

    fun saveMeeting(
        title: String,
        time: String,
        place: String,
        toDoList: List<String>,
        imageUris: List<Uri>
    ) {
        viewModelScope.launch {
            val imageUrls = mutableListOf<String?>()

            imageUris.forEach { uri ->
                val url = repository.uploadImage(uri)
                imageUrls.add(url)
            }

            val meeting = Meeting(
                title = title,
                time = time,
                place = place,
                toDo = toDoList,
                img1 = imageUrls.getOrNull(0),
                img2 = imageUrls.getOrNull(1),
                img3 = imageUrls.getOrNull(2)
            )

            repository.saveMeeting(meeting)
        }
    }

    fun deleteMeeting(meetingId: String?) {
        viewModelScope.launch {
            repository.deleteMeeting(meetingId)
        }
    }

}