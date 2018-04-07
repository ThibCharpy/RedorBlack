package com.stl.tpalt.redorblack.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.AppLog
import com.stl.tpalt.redorblack.model.GameLog
import com.stl.tpalt.redorblack.model.RedOrBlackApp
import com.stl.tpalt.redorblack.utils.AppLogListAdapter
import kotlinx.android.synthetic.main.applog_activity.*


class AppLogActivity : AppCompatActivity()  {

    private val logList: MutableList<GameLog> = RedOrBlackApp.logs
    private val adapter: AppLogListAdapter = AppLogListAdapter(this, logList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_selection)

        listview_loglist.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

}