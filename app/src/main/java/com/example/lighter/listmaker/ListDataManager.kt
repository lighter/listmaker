package com.example.lighter.listmaker

import android.content.Context
import android.preference.PreferenceManager

class ListDataManager(val context: Context) {
    fun saveList(list: ListTask) {
        // 1
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()

        // 2
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())

        // 3
        sharedPreferences.apply()
    }

    fun readLists(): ArrayList<ListTask> {
        // 1
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        // 2
        val sharedPreferenceContents = sharedPreferences.all

        // 3
        val taskLists = ArrayList<ListTask>()

        // 4
        for (taskList in sharedPreferenceContents) {
            val itemsHashSet = taskList.value as HashSet<String>
            val list = ListTask(taskList.key, ArrayList(itemsHashSet))

            // 5
            taskLists.add(list)
        }

        return taskLists
    }
}