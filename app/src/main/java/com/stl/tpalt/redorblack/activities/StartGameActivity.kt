package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.stl.tpalt.redorblack.model.Player
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.RedOrBlackApp

class StartGameActivity : AppCompatActivity() {

    private val playerList: MutableList<Player> = RedOrBlackApp.players

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)

        RedOrBlackApp.generateDeck(playerList.size)

        startActivity(Intent(this, Phase1Activity::class.java))
    }
}
