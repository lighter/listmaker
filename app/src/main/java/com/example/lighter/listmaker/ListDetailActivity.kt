package com.example.lighter.listmaker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: ListTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_list_detail)

        // 1
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)

        // 2
        title = list.name
    }
}
