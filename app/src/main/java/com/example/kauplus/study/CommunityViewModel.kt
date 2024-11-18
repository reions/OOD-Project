package com.example.kauplus.study

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunityViewModel: ViewModel() {
    private val _selectedBodytext = MutableLiveData<Bodytext>(null)
    val selectedBodytext : LiveData<Bodytext> get() = _selectedBodytext

    private val _comments = MutableLiveData<List<Comment>>()
    val comment : LiveData<List<Comment>> get() = _comments

    fun selectPost(bodytext: Bodytext, comments: List<Comment>) {
        _selectedBodytext.value = bodytext
        _comments.value = comments
    }
}