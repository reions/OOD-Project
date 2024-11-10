package com.example.kauplus.ui.meeting

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kauplus.R

class ToDoRVAdapter (private val context: Context, private val todoList: ArrayList<String>) : BaseAdapter() {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater
            .from(context)
            .inflate(R.layout.item_to_do, parent,false)

        val toDo = view.findViewById<TextView>(R.id.text_to_do).run {
            val toDoItem = todoList[position]
            text=toDoItem
        }
        val icCheck=view.findViewById<ImageView>(R.id.check).run {
            setOnClickListener {
                isSelected=!isSelected
            }
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return todoList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return todoList.size
    }
}