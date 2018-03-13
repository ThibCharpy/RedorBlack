package com.stl.tpalt.redorblack

import android.app.Application
import android.os.Bundle
import org.jetbrains.anko.toast

//15 cartes dans la pyramide
//x*52 cartes
//5 cartes par joueur
//(x*52 - 15)/5 = nb de joueurs max
//x=(nbJ*5 + 15)/52
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
            //TODO
        }

        fun onGameStart() : Unit
        {
            //TODO setDecks
            //TODO setPlayers
        }
    }






}