package com.example.lighter.listmaker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ListSelectionRecyclerViewAdapter(val lists: ArrayList<ListTask>, val clickListener: ListSelectionRecyclerViewClickListener): RecyclerView.Adapter<ListSelectionViewHolder>() {

//    private val listTitles = arrayOf("Shopping List", "Chores", "Android Tutorials")

    interface ListSelectionRecyclerViewClickListener {
        fun listItemClicked(list: ListTask)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(p0.context)
                .inflate(R.layout.list_selection_view_holder, p0, false)

        return ListSelectionViewHolder(view)
    }

    override fun getItemCount(): Int {
//        return listTitles.size
        return lists.size
    }

    override fun onBindViewHolder(p0: ListSelectionViewHolder, p1: Int) {
        if (p0 != null) {
            p0.listPosition.text = (p1 + 1).toString()
            p0.listTitle.text = lists[p1].name
            p0.itemView.setOnClickListener {
                clickListener.listItemClicked(lists[p1])
            }
        }
    }

    fun addList(list: ListTask) {
        lists.add(list)

        notifyDataSetChanged()
    }
}