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


//can go to Phase1Activity from here
class PlayerSelectionActivity : AppCompatActivity() {

    private var NumberOfDecks : Int = 0

    private val playerList: MutableList<Player> = RedOrBlackApp.players

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_selection)

        //for testing purpose
        playerList.add(Player("Rudy"))
        playerList.add(Player("Kruissel"))
        playerList.add(Player("Thibthib"))
        playerList.add(Player("Charpy"))
        playerList.add(Player("Yannick"))
        playerList.add(Player("Alain"))
        playerList.add(Player("Djézou"))
        playerList.add(Player("Couassi"))
        playerList.add(Player("Blé"))
        playerList.add(Player("Evan"))
        playerList.add(Player("Joseph"))
        playerList.add(Player("Dat"))
        playerList.add(Player("NGuyen"))

        val adapter = PlayerListAdapter(this, playerList)
        listview_playerlist.adapter = adapter


        button_startgame.setOnClickListener { _ ->
            RedOrBlackApp.generateDeck(number_picker.value)
            val intent = Intent(this, StartGameActivity::class.java)
            startActivity(intent)
            onPause()
        }

        button_add.setOnClickListener { _ ->
            val newplayername : String = input_playername.text.toString()
            if (newplayername == "")
                return@setOnClickListener
            input_playername.setText("")
            playerList.add(Player(newplayername))
            adapter.notifyDataSetChanged()
            updateNumberPicker()
            toast("$newplayername added.\n")
        }

        // Enable the button when the user put text into the editText
        // Else the Button to Add is disable
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
        number_picker.minValue=playerList.size/8+1
    }

}
