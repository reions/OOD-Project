package com.example.kauplus.study

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CommentFirebaseRepository {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("comment")

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> get() = _comments

    init {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val commentsList = mutableListOf<Comment>()
                for (commentSnapshot in snapshot.children) {
                    val comment = commentSnapshot.getValue(Comment::class.java)
                    comment?.firebaseKey = commentSnapshot.key // Firebase 키 저장
                    comment?.let { commentsList.add(it) }
                }
                _comments.value = commentsList
            }

            override fun onCancelled(error: DatabaseError) {
                // 에러 처리
            }
        })
    }

    fun addFirebaseComment(comment: Comment) {
        val key = database.push().key ?: return
        database.child(key).setValue(comment)
    }

    fun deleteCommentByKey(commentKey: String) {
        database.child(commentKey).removeValue()
    }
}