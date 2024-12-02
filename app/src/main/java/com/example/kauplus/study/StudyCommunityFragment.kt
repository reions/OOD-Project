package com.example.kauplus.study

import PostViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kauplus.MainActivity
import com.example.kauplus.databinding.FragmentStudyCommunityBinding


class StudyCommunityFragment : Fragment() {

    private var binding : FragmentStudyCommunityBinding? = null
    private val viewModel: PostViewModel by activityViewModels()

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).hideMoreAndShowBack(false)
        (activity as MainActivity).hideLogoAndShowTitle(false)
        (activity as MainActivity).binding.navText.text="홈화면"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudyCommunityBinding.inflate(inflater, container, false)

        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            val postRVAdapter = PostRVAdapter(posts.toMutableList())
            binding?.rvPosts?.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = postRVAdapter
            }

            postRVAdapter.setMyItemClickListener(object : PostRVAdapter.MyItemClickListener {
                override fun onItemClick(position: Int) {
                    val postId = posts[position].id
                    val fragment = PostDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString("postId", postId)
                        }
                    }
                    viewModel.selectPost("postId")
                    (activity as MainActivity).addFragment(fragment)
                }
            })
        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}