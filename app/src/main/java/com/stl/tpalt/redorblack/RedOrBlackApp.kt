package com.stl.tpalt.redorblack

import android.app.Application
import android.os.Bundle
import org.jetbrains.anko.toast
import java.util.Random

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
        var deck : MutableList<Carte> = mutableListOf()
        var players : MutableList<Player> = mutableListOf()

        fun generateDeck(n : Int) : Unit
        {
            //TODO
        }

        fun pickCardFromDeck() : Carte
        {
            return deck.removeAt(Random().nextInt(deck.size))
        }
    }






}