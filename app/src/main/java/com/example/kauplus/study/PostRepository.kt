import androidx.lifecycle.MutableLiveData
import com.example.kauplus.study.Bodytext
import com.example.kauplus.study.Comment
import com.example.kauplus.study.Posts
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await

class PostRepository {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("posts")

    fun observePosts(posts: MutableLiveData<List<Posts>>) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val postList = mutableListOf<Posts>()
                for (postSnapshot in snapshot.children) {
                    val post = postSnapshot.getValue(Posts::class.java)
                    post?.id = postSnapshot.key
                    post?.let { postList.add(it) }
                }
                posts.postValue(postList)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    suspend fun getBodytext(postId: String): Bodytext? {
        return try {
            val snapshot = database.child(postId).child("bodytext").get().await()
            snapshot.getValue(Bodytext::class.java)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getComments(postId: String): List<Comment> {
        return try {
            val snapshot = database.child(postId).child("comments").orderByChild("comment_time").get().await()
            snapshot.children.mapNotNull { it.getValue(Comment::class.java).apply { this?.firebaseKey = it.key } }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun saveComment(postId: String, comment: Comment) {
        val key = database.child(postId).child("comments").push().key ?: return
        database.child(postId).child("comments").child(key).setValue(comment).await()
    }

    suspend fun updateParticipantCount(postId: String, newCount: Int) {
        try {
            database.child(postId).child("currentParticipants").setValue(newCount).await()
        } catch (e: Exception) {
            // 예외 처리
        }
    }

}
