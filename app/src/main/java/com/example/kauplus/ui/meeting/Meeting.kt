package com.example.kauplus.ui.meeting

import android.net.Uri

data class Meeting(
    var id:String?=null,
    val title:String="",
    val time:String="",
    val place:String="",
    val toDo:List<String> = arrayListOf(),
    val img1:String?=null,
    val img2:String?=null,
    val img3:String?=null,
    val opened:Boolean=true
)
