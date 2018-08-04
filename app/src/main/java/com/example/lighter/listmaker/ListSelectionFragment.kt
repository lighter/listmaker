package com.example.lighter.listmaker

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ListSelectionFragment : Fragment(), ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {
    lateinit var listDataManager: ListDataManager
    lateinit var listsRecyclerView: RecyclerView

    // 1
    private var listener: OnListItemFragmentInteractionListener? = null

    interface OnListItemFragmentInteractionListener {
        fun onListItemClicked(list: ListTask)
    }

    // 2
    companion object {
        fun newInstance(): ListSelectionFragment {
            val fragment = ListSelectionFragment()
            return fragment
        }
    }

    // 3
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnListItemFragmentInteractionListener) {
            listener = context
            listDataManager = ListDataManager(context)
        } else {
            throw RuntimeException(context.toString() + " must implement OnListItemFragmentInteractionListener")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val lists = listDataManager.readLists()
        view?.let {
            listsRecyclerView = it.findViewById(R.id.lists_recycleview)
            listsRecyclerView.layoutManager = LinearLayoutManager(activity)
            listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)
        }
    }

    override fun listItemClicked(list: ListTask) {
        listener?.onListItemClicked(list)
    }

    // 4
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    // 5
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_selection, container, false)
    }

    // 6
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun addList(list: ListTask) {
        listDataManager.saveList(list)

        val recyclerViewAdapter = listsRecyclerView.adapter as ListSelectionRecyclerViewAdapter
        recyclerViewAdapter.addList(list)
    }

    fun saveList(list: ListTask) {
        listDataManager.saveList(list)
        updateLists()
    }

    private fun updateLists() {
        val lists = listDataManager.readLists()
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)
    }
}
