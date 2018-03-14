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


        fun pickCardFromDeck() : Card
        {
            return deck.removeAt(Random().nextInt(deck.size))
//            return Card("lol", 0)
        }
    }
}