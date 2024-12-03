package com.example.kauplus.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kauplus.facility.Reservation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FacilityFirebaseRepository {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("reservations")

    private val _reservations = MutableLiveData<Map<String, List<Reservation>>>()
    val reservations: LiveData<Map<String, List<Reservation>>> get() = _reservations

    init {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val reservationMap = mutableMapOf<String, MutableList<Reservation>>()
                for (reservationSnapshot in snapshot.children) {
                    val reservation = reservationSnapshot.getValue(Reservation::class.java)?.apply {
                        val timeValue = reservationSnapshot.child("time").getValue(Long::class.java)?.toInt()
                            ?: reservationSnapshot.child("time").getValue(String::class.java)?.toIntOrNull()
                            ?: 0
                        this.time = timeValue
                        this.firebaseKey = reservationSnapshot.key
                    }
                    reservation?.let {
                        reservationMap.getOrPut(it.roomName) { mutableListOf() }.add(it)
                    }
                }
                _reservations.value = reservationMap
            }

            override fun onCancelled(error: DatabaseError) {
                // 에러 처리
            }
        })
    }

    fun addReservation(reservation: Reservation) {
        val key = database.push().key ?: return
        database.child(key).setValue(reservation)
    }

    fun deleteReservationByKey(reservationKey: String) {
        database.child(reservationKey).removeValue()
    }
}


