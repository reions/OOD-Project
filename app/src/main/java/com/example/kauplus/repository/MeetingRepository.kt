package com.example.kauplus.repository

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.kauplus.ui.meeting.Meeting
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.util.UUID

class MeetingRepository {
    val database = Firebase.database
    val meetingRef = database.getReference("meeting")
    private val storage = FirebaseStorage.getInstance().reference
    fun observeMeetings(meetings: MutableLiveData<ArrayList<Meeting>>) {
        meetingRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val meetingList = ArrayList<Meeting>()
                for (data in snapshot.children) {
                    val meeting = data.getValue(Meeting::class.java)
                    meeting?.let { meetingList.add(it) }
                }
                meetings.postValue(meetingList)
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    // Meeting 데이터 Firebase에 저장
    suspend fun saveMeeting(meeting: Meeting): Boolean {
        return try {
            val newMeetingRef = meetingRef.push()
            meeting.id = newMeetingRef.key // Firebase ID를 Meeting 객체에 설정
            newMeetingRef.setValue(meeting).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteMeeting(meetingId: String?) {
        if (meetingId!=null){
            meetingRef.child(meetingId).removeValue()
        }
    }

    suspend fun uploadImage(imageUri: Uri): String? {
        val imageRef = storage.child("meetings/${UUID.randomUUID()}.jpg")
        return imageRef.putFile(imageUri).await().storage.downloadUrl.await().toString()
    }

    suspend fun getMeetingById(meetingId: String): Meeting? {
        return try {
            val snapshot = meetingRef.child(meetingId).get().await()
            snapshot.getValue(Meeting::class.java)
        } catch (e: Exception) {
            null
        }
    }

}