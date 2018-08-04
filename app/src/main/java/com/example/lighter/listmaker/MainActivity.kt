package com.example.lighter.listmaker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListSelectionFragment.OnListItemFragmentInteractionListener {

    private var listSelectionFragment: ListSelectionFragment = ListSelectionFragment.newInstance()
    private var fragmentContainer: FrameLayout? = null

    companion object {
        val INTENT_LIST_KEY = "list"
        val LIST_DETAIL_REQUEST_CODE = 123
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        fragmentContainer = findViewById(R.id.fragment_container)

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, listSelectionFragment).commit()

        fab.setOnClickListener { view ->
            showCreateListDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LIST_DETAIL_REQUEST_CODE) {
            data?.let {
                listSelectionFragment.saveList(data.getParcelableArrayExtra<ListTask>(INTENT_LIST_KEY))
            }
        }
    }

    private fun showCreateListDialog() {
        // 1
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        // 2
        val builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)

        // 3
        builder.setPositiveButton(positiveButtonTitle) { dialog, _ ->
            val list = ListTask(listTitleEditText.text.toString())
            listSelectionFragment.addList(list)

            dialog.dismiss()
            showListDetail(list)
        }

        // 4
        builder.create().show()
    }

    private fun showListDetail(list: ListTask) {
        // 1
        val listDetailIntent = Intent(this, ListDetailActivity::class.java)

        // 2
        listDetailIntent.putExtra(INTENT_LIST_KEY, list)

        // 3
        startActivityForResult(listDetailIntent, LIST_DETAIL_REQUEST_CODE)
    }

    override fun onListItemClicked(list: ListTask) {
        showListDetail(list)
    }
}
