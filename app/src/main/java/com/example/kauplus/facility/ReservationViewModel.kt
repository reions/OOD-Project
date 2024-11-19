package com.example.kauplus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kauplus.facility.Reservation
import com.example.kauplus.repository.FirebaseRepository

class ReservationViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository()
    private val _reservations = MutableLiveData<MutableList<Reservation>>(mutableListOf())
    val reservations: LiveData<MutableList<Reservation>> get() = _reservations

    init {
        firebaseRepository.reservations.observeForever { firebaseReservations ->
            _reservations.value = firebaseReservations.toMutableList()
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

        val updatedList = _reservations.value ?: mutableListOf()
        updatedList.remove(reservation)
        _reservations.value = updatedList
    }
}
