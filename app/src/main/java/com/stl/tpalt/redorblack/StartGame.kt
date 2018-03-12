package com.stl.tpalt.redorblack

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class StartGame : AppCompatActivity() {


    var playerList= arrayListOf<String>()
    var NumberOfDecks : Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)

        val nbDecks : Int = intent.extras.getInt("NumberOfDecks")
    }
}
