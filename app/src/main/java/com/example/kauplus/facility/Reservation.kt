package com.example.kauplus.facility

data class Reservation(
    val roomName: String = "",
    val time: String = "",
    val purpose: String = "",
    var firebaseKey: String? = null // Firebase 키 추가
)
