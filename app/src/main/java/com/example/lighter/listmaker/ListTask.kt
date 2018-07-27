package com.example.lighter.listmaker

import android.os.Parcel
import android.os.Parcelable

class ListTask(val name: String, val tasks: ArrayList<String> = ArrayList()) : Parcelable {
    // 1
    constructor(source: Parcel) : this(
            source.readString(),
            source.createStringArrayList()
    )

    override fun describeContents() = 0

    // 2
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeStringList(tasks)
    }

    // 3
    companion object CREATOR: Parcelable.Creator<ListTask> {
        // 4
        override fun createFromParcel(source: Parcel): ListTask = ListTask(source)
        override fun newArray(size: Int): Array<ListTask?> = arrayOfNulls(size)
    }

}