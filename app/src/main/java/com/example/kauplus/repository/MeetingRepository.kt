package com.example.kauplus.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MeetingRepository {
    val database = Firebase.database
    val meetingRef = database.getReference("meeting")

    fun observeMeeting(meeting: MutableLiveData<String>){
        meetingRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                meeting.postValue(snapshot.value.toString())
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    fun postMeeting(newValue:String){
        meetingRef.setValue(newValue)
    }
}