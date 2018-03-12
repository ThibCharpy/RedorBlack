package com.stl.tpalt.redorblack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import com.shawnlin.numberpicker.NumberPicker
import org.jetbrains.anko.toast
import kotlinx.android.synthetic.main.activity_player_selection.*

class PlayerSelection : AppCompatActivity() {
    lateinit var layoutNbDecks : ConstraintLayout
    lateinit var numberPickerNbDecks : NumberPicker
    lateinit var textViewNbDecks : TextView
    lateinit var layoutPlayerList : ConstraintLayout
    lateinit var listviewPlayerList : ListView
    lateinit var layoutNewPlayer : ConstraintLayout
    lateinit var buttonAddNewPlayer : Button
    lateinit var editTextPlayerName : EditText
    lateinit var layoutStartGame : ConstraintLayout
    lateinit var buttonStartGame : Button

    //15 cartes dans la pyramide
    //x*52 cartes
    //5 cartes par joueur
    //(x*52 - 15)/5 = nb de joueurs max
    //x=(nbJ*5 + 15)/52



    lateinit var playerList: PlayerList
    var NumberOfDecks : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_selection)

        layoutNbDecks       = layout_nbdecks
        numberPickerNbDecks = number_picker
        textViewNbDecks     = tv_nbdecks

        layoutPlayerList    = layout_playerlist
        listviewPlayerList  = listview_playerlist

        layoutNewPlayer     = layout_newplayer
        buttonAddNewPlayer  = button_add
        editTextPlayerName  = input_playername

        layoutStartGame     = layout_startgame
        buttonStartGame     = button_startgame


//        val adapter : ArrayAdapter<Player> = ArrayAdapter(this, android.R.layout.simple_list_item_1, playerList)
        
        //button binding
        buttonStartGame.setOnClickListener { _ ->
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("NumberOfDecks", NumberOfDecks)
            intent.putExtra("playersList", playerList)
            startActivity(intent)
            onPause()
        }
        buttonAddNewPlayer.setOnClickListener { _ ->
            val newplayername : String = editTextPlayerName.text.toString()
            if (newplayername == "")
                return@setOnClickListener
            editTextPlayerName.setText("")
            playerList.pl_list.add(Player(newplayername))
            updateNumberPicker()
//            toast("newplayername added.")
            toast("$newplayername added.\n$playerList")
        }

        //edittext
        editTextPlayerName.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                buttonAddNewPlayer.isEnabled=!p0.isNullOrBlank()
            }
        })




    }

    //functions
    fun updateNumberPicker() {
        numberPickerNbDecks.maxValue=Math.max(1, playerList.pl_list.size)
        numberPickerNbDecks.minValue=playerList.pl_list.size/8+1
    }

    override fun finish() {
        super.finish()
    }
}
