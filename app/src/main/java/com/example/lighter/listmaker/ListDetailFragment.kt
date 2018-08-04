package com.example.lighter.listmaker

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ListDetailFragment : Fragment() {

    lateinit var listItemsRecyclerView: RecyclerView
    lateinit var list: ListTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list = arguments.getParcelable(MainActivity.INTENT_LIST_KEY)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_list_detail, container, false)

        view?.let {
            listItemsRecyclerView = it.findViewById(R.id.list_item_recyclerview)
            listItemsRecyclerView.adapter = ListItemsRecyclerViewAdapter(list)
            listItemsRecyclerView.layoutManager = LinearLayoutManager(activity)
        }

        return view
    }

    fun addTask(item: String) {
        list.tasks.add(item)

        val listRecyclerAdapter = listItemsRecyclerView.adapter as ListItemsRecyclerViewAdapter
        listRecyclerAdapter.list = list
        listRecyclerAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(list: ListTask): ListDetailFragment {
            val fragment = ListDetailFragment()
            val args = Bundle()
            args.putParcelable(MainActivity.INTENT_LIST_KEY, list)
            fragment.arguments = args
            return fragment
        }
    }
}
