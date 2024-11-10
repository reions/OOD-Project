package com.example.kauplus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kauplus.repository.MeetingRepository

class MeetingViewModel : ViewModel() {
    private val _itemSchedule=MutableLiveData<String>()
    val itemSchedule:LiveData<String> get()=_itemSchedule

    private val repository = MeetingRepository()
    init {
        repository.observeMeeting(_itemSchedule)
    }

    private fun modifyMeeting(position:Int){
        val newMeeting = _itemSchedule.toString()
        repository.postMeeting(newMeeting)
    }


}