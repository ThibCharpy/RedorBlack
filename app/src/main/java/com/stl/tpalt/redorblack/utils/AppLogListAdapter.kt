package com.stl.tpalt.redorblack.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.GameLog


/**
 * Created by I346992 on 12/03/2018.
 */
class AppLogListAdapter(var activity: Activity, var logs: MutableList<GameLog>) : BaseAdapter() {
    @SuppressLint("InflateParams", "ViewHolder")
    override fun getView(index: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view: View
        view = inflater.inflate(R.layout.apploglist_item,null)

        val appLog = view.findViewById<TextView>(R.id.appLog)
        appLog.text = logs[index].text
        val appLogIcon = view.findViewById<ImageView>(R.id.appLogIcon)
        appLogIcon.setImageResource(logs[index].cardId)

        return view
    }

    override fun getItem(index: Int): Any {
        return logs[index]
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getCount(): Int {
        return logs.size
    }

}