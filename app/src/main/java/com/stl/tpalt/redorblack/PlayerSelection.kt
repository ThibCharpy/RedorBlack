package com.stl.tpalt.redorblack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import com.shawnlin.numberpicker.NumberPicker
import org.jetbrains.anko.toast
import kotlinx.android.synthetic.main.activity_player_selection.*

//15 cartes dans la pyramide
//x*52 cartes
//5 cartes par joueur
//(x*52 - 15)/5 = nb de joueurs max
//x=(nbJ*5 + 15)/52

class PlayerSelection : AppCompatActivity() {
    lateinit var numberPickerNbDecks : NumberPicker
    lateinit var buttonAddNewPlayer : Button
    lateinit var editTextPlayerName : EditText
    lateinit var buttonStartGame : Button

    lateinit var playerListView: ListView


    lateinit var playerList: PlayerList
    var NumberOfDecks : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_selection)

        numberPickerNbDecks = findViewById(R.id.number_picker)

        buttonAddNewPlayer  = findViewById(R.id.button_add)
        editTextPlayerName  = findViewById(R.id.input_playername)

        buttonStartGame     = findViewById(R.id.button_startgame)

        playerListView = findViewById(R.id.listview_playerlist)
        val player_list = mutableListOf(
                Player("Rudy"),
                Player("Yannick"),
                Player("Thibault"))
        playerList = PlayerList()

        val adapter = PlayerListAdapter(this, player_list)
        playerListView.adapter = adapter

        
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
            /*if (newplayername == "")
                return@setOnClickListener*/
            editTextPlayerName.setText("")
            //playerList.pl_list.add(Player(newplayername))
            player_list.add(Player(newplayername))
            adapter.notifyDataSetChanged()
            updateNumberPicker()
            toast("$newplayername added.\n$playerList")
        }

        // Enable the button when the user put text into the editText
        // Else the Button to Add is disable
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

}
