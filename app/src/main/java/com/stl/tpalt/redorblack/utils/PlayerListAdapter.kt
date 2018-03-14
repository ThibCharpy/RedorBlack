package com.stl.tpalt.redorblack.utils

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.stl.tpalt.redorblack.model.Player
import com.stl.tpalt.redorblack.R

/**
 * Created by I346992 on 12/03/2018.
 */
class PlayerListAdapter(var activity: Activity, var players: MutableList<Player>) : BaseAdapter() {
    override fun getView(index: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view: View
        view = inflater.inflate(R.layout.playerlist_item,null)

        val playerPseudo = view.findViewById<TextView>(R.id.player_pseudo)
        playerPseudo.text = players.get(index).name

        return view
    }

    override fun getItem(index: Int): Any {
        return players[index]
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getCount(): Int {
        return players.size
    }

}