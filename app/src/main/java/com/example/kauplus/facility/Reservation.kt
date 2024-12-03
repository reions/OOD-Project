package com.example.kauplus.facility

data class Reservation(
    val roomName: String = "",
    var time: Int = 0,
    val purpose: String = "",
    var firebaseKey: String? = null // Firebase 키 추가
)
