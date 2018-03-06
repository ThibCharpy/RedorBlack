package com.stl.tpalt.redorblack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.jetbrains.anko.toast

class PlayerSelection : AppCompatActivity() {

    //15 cartes dans la pyramide
    //x*52 cartes
    //5 cartes par joueur
    //(x*52 - 15)/5 = nb de joueurs max
    //x=(nbJ*5 + 15)/52

    var playerList= arrayListOf<String>()
    var NumberOfDecks : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_selection)

        val layoutNbDecks       = findViewById<ConstraintLayout>(R.id.layout_nbdecks)
        val numberPickerNbDecks = findViewById<com.travijuu.numberpicker.library.NumberPicker>(R.id.numberpicker_nbdecks)
        val textViewNbDecks     = findViewById<TextView>(R.id.tv_nbdecks)

        val layoutPlayerList = findViewById<ConstraintLayout>(R.id.layout_playerlist)
        val playerListView   = findViewById<RecyclerView>(R.id.recyclerview_playerlist)

        val layoutNewPlayer    = findViewById<ConstraintLayout>(R.id.layout_newplayer)
        val buttonAddNewPlayer = findViewById<Button>(R.id.button_add)
        val editTextPlayerName = findViewById<EditText>(R.id.input_playername)

        val layoutStartGame = findViewById<ConstraintLayout>(R.id.layout_startgame)
        val buttonStartGame = findViewById<Button>(R.id.button_startgame)

//        toast(numberPickerNbDecks.value)
        numberPickerNbDecks.min=1
        numberPickerNbDecks.max=99

        //button binding
        buttonStartGame.setOnClickListener { _ ->
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("NumberOfDecks", NumberOfDecks)
            intent.putStringArrayListExtra("playersList", playerList)
            startActivity(intent)
            onPause()
        }
        buttonAddNewPlayer.setOnClickListener { _ ->
            //set min and max for numberPicker
            val max = playerList.size*1+1
            numberPickerNbDecks.max=max
            val min = playerList.size/8+1
            numberPickerNbDecks.min=min

            val newplayer : String = editTextPlayerName.text.toString()
            editTextPlayerName.setText("")
            playerList.add(newplayer)

//            toast("$newplayer added.")
            toast("$newplayer added.\nmin : $min\nmax : $max")
        }

    }

    override fun finish() {
        super.finish()
    }
}
