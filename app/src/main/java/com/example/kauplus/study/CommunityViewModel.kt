package com.example.kauplus.study

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunityViewModel : ViewModel() {
    private val _posts = MutableLiveData<List<Posts>>()
    val posts: LiveData<List<Posts>> get() = _posts

    private val bodytextMap = mutableMapOf<String, Bodytext>()
    private val commentMap = mutableMapOf<String, List<Comment>>()

    private val _selectedBodytext = MutableLiveData<Bodytext>()
    val selectedBodytext: LiveData<Bodytext> get() = _selectedBodytext

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> get() = _comments

    init {
        // 초기 데이터 설정
        val initialPosts = listOf(
            Posts("1","알고리즘 스터디 멤버 구합니다.", "14 : 00 ~ 15 : 00", "C1 스터디룸", "3/5"),
            Posts("2","러닝 크루 모집", "14 : 00 ~ 15 : 00", "운동장", "2/5")
        )
        _posts.value = initialPosts

        // 각 Post의 Bodytext 및 Comment 설정
        bodytextMap["1"] = Bodytext(
            "알고리즘 스터디 멤버 구합니다",
            "내일 도서관 스터디룸에서 함께 알고리즘 공부할 동기 모집합니다.\n남녀 학년 상관 없으니 부담없이 참여해주시면 감사하겠습니다.",
            "14 : 00 ~ 15 : 00",
            "C1 스터디룸"
        )
        bodytextMap["2"] = Bodytext(
            "러닝 크루 모집",
            "같이 학교 운동장에서 러닝 하실 크루원들을 모집합니다.\n남녀, 학년 상관없고 잘뛰지 못해도 괜찮으니 많은 참여 바랍니다.",
            "14 : 00 ~ 15 : 00",
            "운동장"
        )

        commentMap["1"] = listOf(
            Comment("멤버가 합이 잘 맞을 경우 정기 스터디 진행할 계획이 있습니다.", "10/22 18:05", "helloworld!08"),
            Comment("아직도 구하나요?", "10/22 18:12", "송동호")
        )
        commentMap["2"] = listOf(
            Comment("러닝 초보도 참가 가능한가요?", "10/22 19:00", "민지"),
            Comment("운동장 어디서 만나나요?", "10/22 19:15", "준영")
        )
    }

    fun selectPost(postId: String) {
        val bodytext = bodytextMap[postId]
        if (bodytext != null) {
            _selectedBodytext.value = bodytext!!
            _comments.value = commentMap[postId] ?: emptyList()
        } else {
            // 기본값 설정 또는 오류 처리
            _selectedBodytext.value = Bodytext("제목 없음", "내용 없음", "", "")
            _comments.value = emptyList()
        }
    }
    fun setSelectedBodytext(bodytext: Bodytext) {
        _selectedBodytext.value = bodytext
    }


    fun addComment(postId: String, newComment: Comment) {
        val updatedComments = commentMap[postId]?.toMutableList() ?: mutableListOf()
        updatedComments.add(newComment)
        commentMap[postId] = updatedComments // 갱신된 리스트 설정
        _comments.value = updatedComments // LiveData 갱신
    }
}
