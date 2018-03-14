package com.stl.tpalt.redorblack.model

import android.app.Application
import com.stl.tpalt.redorblack.R

/**
 * Created by thibault on 14/03/18.
 */

sealed class Entity
data class Card(var cardname:String, var image: Int) : Entity()
data class Player(var name: String) : Entity()


class RedOrBlackApp : Application()
{
    override fun onCreate() {
        super.onCreate()
    }

    //global vars
    companion object {
        var deck : List<Card> = listOf<Card>()
        var temporaryplayers : MutableList<Player> = mutableListOf()
        var cartesdesjoueurs : HashMap<Player, MutableList<Card>> = hashMapOf()
        var players : MutableList<Player> = mutableListOf()

        fun generateDeck(countOfDeck : Int): MutableList<Card> {
            when(countOfDeck){
                0 -> return listOf<Card>() as MutableList<Card>
                else ->
                    return listOf<List<Card>>(buildDeck(), generateDeck(countOfDeck - 1))
                            .flatten() as MutableList<Card>
            }
        }

        fun buildDeck(): List<Card> {
            val values = listOf<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
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
            //return deck.removeAt(Random().nextInt(deck.size))
            return Card("lol", 0)
        }
    }
}