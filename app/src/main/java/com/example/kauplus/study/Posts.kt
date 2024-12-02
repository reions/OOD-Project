package com.example.kauplus.study

data class Posts(
    var id: String? = null,
    val posting_title: String = "",
    val time: String = "",
    val space: String = "",
    val participant: String = "",
    var firebaseKey: String? = null
)
data class Bodytext(
    val in_post_title: String = "",
    val body_text: String = "",
    val time_to_meet: String = "",
    val group_space: String = ""
)
data class Comment (
    val comment :String ="",
    val comment_time : String = "",
    val comment_writer : String = "",
    var firebaseKey: String? = null // Firebase 키 추가

)