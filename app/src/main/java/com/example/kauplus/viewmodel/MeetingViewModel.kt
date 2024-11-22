package com.example.kauplus.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kauplus.repository.MeetingRepository
import com.example.kauplus.ui.meeting.Meeting
import kotlinx.coroutines.launch

class MeetingViewModel : ViewModel() {
    private val repository = MeetingRepository()

    private val _openedSchedule=MutableLiveData<ArrayList<Meeting>>()
    val openedSchedule : LiveData<ArrayList<Meeting>> = _openedSchedule

    private val _closedSchedule=MutableLiveData<ArrayList<Meeting>>()
    val closedSchedule : LiveData<ArrayList<Meeting>> = _closedSchedule

    private val _selectedMeeting = MutableLiveData<Meeting>()
    val selectedMeeting: LiveData<Meeting> = _selectedMeeting

    init {
        repository.observeMeetings(_openedSchedule,_closedSchedule)
    }

    fun closeMeeting(meetingId: String) {
        viewModelScope.launch {
            repository.fetchCloseMeeting(meetingId)
        }
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
        imageUris: List<Uri?>
    ) {
        viewModelScope.launch {
            val meeting = Meeting(
                title = title,
                time = time,
                place = place,
                toDo = toDoList,
                img1 = imageUris.getOrNull(0).toString(),
                img2 = imageUris.getOrNull(1).toString(),
                img3 = imageUris.getOrNull(2).toString()
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