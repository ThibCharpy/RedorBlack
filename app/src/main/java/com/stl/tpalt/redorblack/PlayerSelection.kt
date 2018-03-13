package com.stl.tpalt.redorblack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import org.jetbrains.anko.toast
import kotlinx.android.synthetic.main.activity_player_selection.*

//15 cartes dans la pyramide
//x*52 cartes
//5 cartes par joueur
//(x*52 - 15)/5 = nb de joueurs max
//x=(nbJ*5 + 15)/52

class PlayerSelection : AppCompatActivity() {

    var NumberOfDecks : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_selection)


        val playerList = mutableListOf(
                Player("Rudy1"),
                Player("Thibthib1"),
                Player("Rudy2"),
                Player("Thibthib2"),
                Player("Rudy3"),
                Player("Thibthib3"),
                Player("Rudy4"),
                Player("Thibthib4"),
                Player("Rudy5"),
                Player("Thibthib5"),
                Player("Rudy6"),
                Player("Thibthib6"))
//        playerList = PlayerList()

        val adapter = PlayerListAdapter(this, playerList)
        listview_playerlist.adapter = adapter

        
        //button binding
        button_startgame.setOnClickListener { _ ->
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("NumberOfDecks", NumberOfDecks)
//            intent.putExtra("playersList", playerList)
            startActivity(intent)
            onPause()
        }

        button_add.setOnClickListener { _ ->
            val newplayername : String = input_playername.text.toString()
            /*if (newplayername == "")
                return@setOnClickListener*/
            input_playername.setText("")
            //playerList.pl_list.add(Player(newplayername))
            playerList.add(Player(newplayername))
            adapter.notifyDataSetChanged()
            updateNumberPicker()
            toast("$newplayername added.\n")
        }

        // Enable the button when the user put text into the editText
        // Else the Button to Add is disable
        input_playername.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                button_add.isEnabled=!p0.isNullOrBlank()
            }
        })




    }

    //functions
    fun updateNumberPicker() {
//        number_picker.maxValue=Math.max(1, playerList.pl_list.size)
//        number_picker.minValue=playerList.pl_list.size/8+1
    }

}
