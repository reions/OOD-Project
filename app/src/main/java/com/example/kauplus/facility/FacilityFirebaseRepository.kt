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

    private val _reservations = MutableLiveData<List<Reservation>>()
    val reservations: LiveData<List<Reservation>> get() = _reservations

    init {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val reservationList = mutableListOf<Reservation>()
                for (reservationSnapshot in snapshot.children) {
                    val reservation = reservationSnapshot.getValue(Reservation::class.java)
                    reservation?.firebaseKey = reservationSnapshot.key // Firebase 키 저장
                    reservation?.let { reservationList.add(it) }
                }
                _reservations.value = reservationList
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
