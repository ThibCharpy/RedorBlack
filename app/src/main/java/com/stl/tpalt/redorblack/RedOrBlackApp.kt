package com.stl.tpalt.redorblack

import android.app.Application
import android.os.Bundle
import org.jetbrains.anko.toast

/**
 * Created by I346992 on 12/03/2018.
 */
class RedOrBlackApp : Application()
{
    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        var deck : MutableList<Carte> = mutableListOf()
        var temporaryplayers : MutableList<Player> = mutableListOf()
        var players : MutableList<Player> = mutableListOf()
        var cartesdesjoueurs : HashMap<Player, MutableList<Carte>> = hashMapOf()
    }

    fun generateDecks(n : Int) : Unit
    {
        //TODO
        toast("TODO generate decks")
    }

    fun addPlayer(name : String)
    {
        temporaryplayers.add(Player(name))
//        temporaryplayers.add(Player(name, null, null, null, null, null))
    }
}