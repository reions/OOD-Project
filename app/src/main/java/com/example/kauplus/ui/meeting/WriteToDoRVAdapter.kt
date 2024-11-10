package com.example.kauplus.ui.meeting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.kauplus.R
import com.example.kauplus.databinding.ItemMeetingBinding
import com.example.kauplus.databinding.ItemWriteToDoBinding

class WriteToDoRVAdapter (itemList: MutableList<String>): RecyclerView.Adapter<WriteToDoRVAdapter.WriteToDoRVViewHolder>() {

    var editList: MutableList<String> =itemList
        set(value){
            field=value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WriteToDoRVAdapter.WriteToDoRVViewHolder {
        return WriteToDoRVViewHolder(
            ItemWriteToDoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WriteToDoRVViewHolder, position: Int) {
        holder.edit.setText(editList[position])
        holder.edit.addTextChangedListener {
            editList[position] = it.toString()
        }
        holder.itemView.setOnClickListener {
            myItemClickListener.onItemClick(position)
        }
    }

    interface MyItemClickListener{
        fun onItemClick(position: Int)

    }
    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        myItemClickListener=itemClickListener
    }

    inner class WriteToDoRVViewHolder(private val binding: ItemWriteToDoBinding) :
        RecyclerView.ViewHolder(binding.root){
        val edit=binding.etToDo
    }
    override fun getItemViewType(position: Int): Int =position

    override fun getItemCount(): Int =editList.size


}