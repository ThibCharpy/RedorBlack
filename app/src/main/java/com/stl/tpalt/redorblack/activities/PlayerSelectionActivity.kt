package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.stl.tpalt.redorblack.model.Player
import com.stl.tpalt.redorblack.utils.PlayerListAdapter
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.RedOrBlackApp
import org.jetbrains.anko.toast
import kotlinx.android.synthetic.main.activity_player_selection.*


//can go to StartGameActivity from here
class PlayerSelectionActivity : AppCompatActivity() {

    private var NumberOfDecks : Int = 0

    private val playerList: MutableList<Player> = RedOrBlackApp.players

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_selection)

        //for testing purpose
        playerList.add(Player("Tomo", arrayOfNulls(5)))
        playerList.add(Player("Tomshiro", arrayOfNulls(5)))
        playerList.add(Player("Kenshiro", arrayOfNulls(5)))

        val adapter = PlayerListAdapter(this, playerList)
        listview_playerlist.adapter = adapter

        button_startgame.setOnClickListener { _ ->
            if (RedOrBlackApp.players.size < 1)
            {
                toast(R.string.emptyplayerlist)
                return@setOnClickListener
            }
            val intent = Intent(this, StartGameActivity::class.java)
            intent.putExtra("nbJeuCartes", number_picker.value)
            startActivity(intent)
            onPause()
        }

        button_add.setOnClickListener { _ ->
            val newplayername : String = input_playername.text.toString().trim().replace("\\s+".toRegex(), " ")
            if (newplayername == "")
                return@setOnClickListener
            if (playerList.any { p -> p.name.toLowerCase()==newplayername.toLowerCase() }) {
                toast(R.string.playeralreadyexists)
                return@setOnClickListener
            }
            else
                input_playername.setText("")
            playerList.add(Player(newplayername, arrayOfNulls(5)))
            adapter.notifyDataSetChanged()
            updateNumberPicker()
        }

        input_playername.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                button_add.isEnabled=!p0.isNullOrBlank()
            }
        })
    }

    //need to be called after changing (add / suppress) list of players
    private fun updateNumberPicker() {
        number_picker.maxValue=Math.max(1, playerList.size)
        number_picker.wrapSelectorWheel = false
        number_picker.minValue=playerList.size/8+1
        number_picker.wrapSelectorWheel = false
    }

}
