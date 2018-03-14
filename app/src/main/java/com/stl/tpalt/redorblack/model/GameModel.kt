package com.stl.tpalt.redorblack.model

import android.app.Application
import com.stl.tpalt.redorblack.R
import java.util.*

/**
 * Created by thibault on 14/03/18.
 */

sealed class Entity
data class Card(var cardname:String, var image: Int) : Entity()
data class Player(var name: String) : Entity()


class RedOrBlackApp : Application()
{
    //global vars
    companion object {
        var deck : MutableList<Card> = mutableListOf<Card>(Card("NaN", 0))
        var players : MutableList<Player> = mutableListOf()

        fun generateDeck(n : Int)
        {
            deck = generateDeckRec(n)
        }

        private fun generateDeckRec(countOfDeck : Int): MutableList<Card>
        {
            return when(countOfDeck){
                0 ->    listOf<Card>() as MutableList<Card>
                else -> listOf<List<Card>>(generateOneDeck(), generateDeckRec(countOfDeck - 1))
                        .flatten() as MutableList<Card>
            }
        }

        private fun generateOneDeck(): List<Card>
        {
            val values = listOf<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "t", "j", "q", "k")
            val signs = listOf<String>("Spades", "Clubs", "Heart", "Diamonds")
            return values.map { value ->
                signs.map { sign ->
                    val code: String = sign[0].toLowerCase() + value
                    when (sign) {
                        "Spades" ->
                            Card(code, R.drawable.s)
                        "Clubs" ->
                            Card(code, R.drawable.c)
                        "Heart" ->
                            Card(code, R.drawable.h)
                        "Diamonds" ->
                            Card(code, R.drawable.d) //
                        else -> throw IllegalArgumentException("Bad Color Matching");
                    }
                }
            }.flatten()
        }

        fun pickCardFromDeck() : Card
        {
            return deck.removeAt(Random().nextInt(deck.size))
//            return Card("lol", 0)
        }
    }
}