package com.stl.tpalt.redorblack

import android.app.Application

/**
 * Created by I346992 on 12/03/2018.
 */
class RedOrBlackApp : Application()
{
    override fun onCreate() {
        super.onCreate()
    }

    //global vars
    companion object {
        var deck : List<Carte> = listOf<Carte>()
        var temporaryplayers : MutableList<Player> = mutableListOf()
        var players : MutableList<Player> = mutableListOf()
        var cartesdesjoueurs : HashMap<Player, MutableList<Carte>> = hashMapOf()
        fun generateDeck(countOfDeck : Int): MutableList<Carte> {
            when(countOfDeck){
                0 -> return listOf<Carte>() as MutableList<Carte>
                else ->
                    return listOf<List<Carte>>(buildDeck(),generateDeck(countOfDeck-1))
                            .flatten() as MutableList<Carte>
            }
        }
        fun buildDeck(): List<Carte> {
            val values = listOf<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
            val signs = listOf<String>("Spades", "Clubs", "Heart", "Diamonds")
            return values.map { value ->
                signs.map { sign ->
                    val code: String = sign[0].toLowerCase() + value
                    when (sign) {
                        "Spades" ->
                            Carte(code, R.drawable.s)
                        "Clubs" ->
                            Carte(code, R.drawable.c)
                        "Heart" ->
                            Carte(code, R.drawable.h)
                        "Diamonds" ->
                            Carte(code, R.drawable.d) //
                        else -> throw IllegalArgumentException("Bad Color Matching");
                    }
                }
            }.flatten()
        }
    }


    fun generateDecks(n : Int) : Unit
    {

    }

    fun addPlayer(name : String)
    {
        temporaryplayers.add(Player(name))
    }
}