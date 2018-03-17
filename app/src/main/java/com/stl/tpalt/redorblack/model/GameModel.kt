package com.stl.tpalt.redorblack.model

import android.app.Application
import android.util.Log
import java.util.*

sealed class Entity

data class Card(
        var cardname:String,
        var image: Int
) : Entity()
{
    fun getValue() : Int
    {
        return when(cardname[1])
        {
            'k', 'r' -> 13
            'q', 'd' -> 12
            'j', 'v' -> 11
            't'      -> 10
            else     -> cardname[1].toInt()-'0'.toInt()
        }
    }
}
@Suppress("ArrayInDataClass")
data class Player(
        var name: String = "PlayerName",
        var cartes : Array<Card?> = arrayOfNulls(5),
        var drunk : Int = 0,
        var given : Int = 0
) : Entity()


data class Rules(
        var type : String, //"Normal", "soft", "Hard"
        var phase1sips : Int ,
        var phase2sips : Int ,
        var phase3sips : Int ,
        var phase4sips : Int ,
        var phase5sips : Int ,
        var randomFrequence : Double,
        var bonusForEquals : Int
)


class RedOrBlackApp : Application()
{
    //global vars
    companion object {
        var deck : MutableList<Card> = mutableListOf<Card>(Card("NaN", 0))
        var players : MutableList<Player> = mutableListOf()
        var rules : Rules = Rules("Normal", 1, 2, 3, 4, 5, 0.1, 1)
        const val masked: Float = 0.6F

        fun pickCardFromDeck() : Card
        {
            Log.i("deck :", deck.map { c -> c.cardname }.toString())
            if (deck.size==0)
            {
                Log.e("Error", "Card Picked on an empty deck")
                return Card("NaN", 0)
            }
            return deck.removeAt(Random().nextInt(deck.size))
        }

        fun getPlayerForPhase(phase: Int): Player? {
            players.forEach { p ->
                if (p.cartes[phase-1]==null)
                {
                    return p
                }
            }
            return null
        }



    }
}