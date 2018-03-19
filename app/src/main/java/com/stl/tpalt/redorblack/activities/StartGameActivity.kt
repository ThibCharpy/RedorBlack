package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.Card
import com.stl.tpalt.redorblack.model.RedOrBlackApp

class StartGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)

        RedOrBlackApp.players.map { j -> j.cartes= arrayOfNulls(5) }
        RedOrBlackApp.deck = generateDeckRec(intent.getIntExtra("nbJeuCartes", 2))
        Log.w("test", "start phase 1")
        startActivity(Intent(this, Phase1Activity::class.java))
        finish()
    }

//    override fun onResume() {
//        super.onResume()
//        startActivity(Intent(this, PlayerSelectionActivity::class.java))
//    }

    private fun generateDeckRec(countOfDeck : Int): MutableList<Card>
    {
        return when(countOfDeck){
            1 ->    generateOneDeck() as MutableList<Card>
            else -> listOf(generateOneDeck(), generateDeckRec(countOfDeck - 1))
                    .flatten() as MutableList<Card>
        }
    }

    private fun generateOneDeck(): List<Card>
    {
        val values = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "t", "v", "d", "r")
        val signs = listOf("Spades", "Clubs", "Heart", "Diamonds")
        return values.map { value ->
            signs.map { sign ->
                val code: String = sign[0].toLowerCase() + value
                when (sign) {
                    "Spades" ->
                        Card(code, resources.getIdentifier(code, "drawable", packageName))
                    "Clubs" ->
                        Card(code, resources.getIdentifier(code, "drawable", packageName))
                    "Heart" ->
                        Card(code, resources.getIdentifier(code, "drawable", packageName))
                    "Diamonds" ->
                        Card(code, resources.getIdentifier(code, "drawable", packageName)) //
                    else -> throw IllegalArgumentException("Bad Color Matching")
                }
            }
        }.flatten()
    }

}
