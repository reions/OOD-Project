import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kauplus.study.Bodytext
import com.example.kauplus.study.Comment
import com.example.kauplus.study.Posts
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val repository = PostRepository()

    private val _posts = MutableLiveData<List<Posts>>()
    val posts: LiveData<List<Posts>> = _posts

    private val _selectedBodytext = MutableLiveData<Bodytext?>()
    val selectedBodytext: LiveData<Bodytext?> = _selectedBodytext

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> = _comments

    init {
        repository.observePosts(_posts)
    }

    fun fetchBodytext(postId: String) {
        viewModelScope.launch {
            val bodytext = repository.getBodytext(postId)
            _selectedBodytext.postValue(bodytext)
        }
    }
    fun selectPost(postId: String) {
        fetchBodytext(postId)
    }

    fun fetchComments(postId: String) {
        viewModelScope.launch {
            val commentList = repository.getComments(postId)
            _comments.postValue(commentList)
        }
    }

    fun addComment(postId: String, comment: Comment) {
        viewModelScope.launch {
            repository.saveComment(postId, comment)
            fetchComments(postId)
        }
    }
    fun updateParticipantCount(postId: String, newCount: Int) {
        viewModelScope.launch {
            repository.updateParticipantCount(postId, newCount)
        }
    }


    fun saveBodytext(postId: String, bodytext: Bodytext) {
        viewModelScope.launch {
            repository.saveBodytext(postId, bodytext)
        }
    }
}
