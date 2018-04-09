package com.stl.tpalt.redorblack.model

import android.app.Application
import android.util.Log
import java.util.*

sealed class Entity
sealed class AppLog (var text: String)

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
        var type : String = "Normal", //"Normal", "soft", "Hard"
        var phase1sipsdrunk : Int = 1,
        var phase2sipsdrunk : Int = 2,
        var phase3sipsdrunk : Int = 3,
        var phase4sipsdrunk : Int = 4,
        var phase5sipsdrunk : Int = 5,

        var phase1sipsgiven : Int = 1,
        var phase2sipsgiven : Int = 1,
        var phase3sipsgiven : Int = 1,
        var phase4sipsgiven : Int = 1,
        var phase5sipsgiven : Int = 1,

        var randomFrequence : Double = 0.1,
        var bonusForEquals : Int=2
)

data class CardPickedEvent(
        var player : Player,
        var cardGotten : Card,
        var won : Boolean,
        var sips : Int
)


data class GameLog(var logText: String, var cardId: Int) : AppLog(logText)

/*data class LogInfo(var time: LocalDateTime, var logText: String) : AppLog(time,"INFO",logText)
data class LogWarning(var time : LocalDateTime, var logText : String) : AppLog(time,"WARNING",logText)
data class LogError(var time : LocalDateTime, var logText : String) : AppLog(time,"ERROR",logText)*/

class RedOrBlackApp : Application()
{
    //global vars
    companion object {
        var deck : MutableList<Card> = mutableListOf(Card("NaN", 0))
        var players : MutableList<Player> = mutableListOf()
        var history : MutableList<CardPickedEvent> = mutableListOf()
        var rules : Rules = Rules()
        var logs: MutableList<GameLog> = mutableListOf()
        var stats : MutableMap<Player,Int> = mutableMapOf()
        const val masked: Float = 0.6F

        fun pickCardFromDeck() : Card
        {
//            Log.i("deck :", deck.map { c -> c.cardname }.toString())
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