package com.example.kauplus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kauplus.facility.Reservation
import com.example.kauplus.repository.FacilityFirebaseRepository

class ReservationViewModel : ViewModel() {
    private val firebaseRepository = FacilityFirebaseRepository()
    private val _reservations = MutableLiveData<Map<String, MutableList<Reservation>>>(mutableMapOf())
    val reservations: LiveData<Map<String, MutableList<Reservation>>> get() = _reservations

    init {
        // Firebase와 동기화
        firebaseRepository.reservations.observeForever { firebaseReservations ->
            _reservations.value = firebaseReservations.mapValues { it.value.toMutableList() }.toMutableMap()
        }
    }

    fun addReservation(reservation: Reservation) {
        firebaseRepository.addReservation(reservation)
    }

    fun deleteReservation(reservation: Reservation) {
        val firebaseKey = reservation.firebaseKey
        if (firebaseKey != null) {
            firebaseRepository.deleteReservationByKey(firebaseKey)
        }

        val roomReservations = _reservations.value?.get(reservation.roomName) ?: mutableListOf()
        roomReservations.remove(reservation)
        _reservations.value = _reservations.value // LiveData 갱신
    }

    fun getReservedTimesForRoom(roomName: String): Set<Int> {
        return _reservations.value?.get(roomName)?.map { it.time }?.toSet() ?: emptySet()
    }
}




