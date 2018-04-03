package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.Card
import com.stl.tpalt.redorblack.model.CardPickedEvent
import com.stl.tpalt.redorblack.model.Player
import com.stl.tpalt.redorblack.model.RedOrBlackApp
import kotlinx.android.synthetic.main.activity_phase5.*
import kotlinx.android.synthetic.main.header.*

class Phase5Activity : AppCompatActivity() {
    val phase : Int = 5
    private lateinit var mycard1 : ImageView
    private lateinit var mycard2 : ImageView
    private lateinit var mycard3 : ImageView
    private lateinit var mycard4 : ImageView
    private var card1value : Int = -1
    private var card2value : Int = -1
    private var card3value : Int = -1
    private var card4value : Int = -1
    private lateinit var jai : ImageView
    private lateinit var jaipas : ImageView
    private var win : Boolean = false
    private lateinit var hiddenCard : Card
    private var newCardValue: Int=-1

    private lateinit var joueur : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase5)
        val playerCurr = RedOrBlackApp.getPlayerForPhase(phase)
        if (playerCurr == null)
        {
            val intent = Intent(this, EndGameActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            joueur=playerCurr
            tv_header.text = playerCurr.name

            mycard1 = phase5_mycard1
            mycard2 = phase5_mycard2
            mycard3 = phase5_mycard3
            mycard4 = phase5_mycard4
            jai=phase5_cardjai
            jaipas=phase5_cardjaipas

            //Ui init
            mycard1.setImageResource(playerCurr.cartes[0]!!.image)
            mycard2.setImageResource(playerCurr.cartes[1]!!.image)
            mycard3.setImageResource(playerCurr.cartes[2]!!.image)
            mycard4.setImageResource(playerCurr.cartes[3]!!.image)
            jai.setImageResource(R.drawable.jai)
            jaipas.setImageResource(R.drawable.jaipas)
            tv_drinkorgive.visibility = View.INVISIBLE
            tv_winorlose.visibility = View.INVISIBLE

            card1value = playerCurr.cartes[0]!!.getValue()
            card2value = playerCurr.cartes[1]!!.getValue()
            card3value = playerCurr.cartes[2]!!.getValue()
            card4value = playerCurr.cartes[3]!!.getValue()
        }
    }
    @Suppress("UNUSED_PARAMETER")
    fun onJaiClicked(v : View)
    {
        hiddenCard = RedOrBlackApp.pickCardFromDeck()
        joueur.cartes[4] = hiddenCard
        newCardValue=hiddenCard.getValue()
        makeBackGroundClickableAfterXsec(1.0)
        if (newCardValue == card1value
                || newCardValue == card2value
                || newCardValue == card3value
                || newCardValue == card4value) {
            win=true
        }
        winlose()
        jai.setImageResource(hiddenCard.image)
        jaipas.alpha=RedOrBlackApp.masked
    }
    @Suppress("UNUSED_PARAMETER")
    fun onJaiPasClicked(v : View)
    {
        hiddenCard = RedOrBlackApp.pickCardFromDeck()
        joueur.cartes[4] = hiddenCard
        newCardValue=hiddenCard.getValue()
        makeBackGroundClickableAfterXsec(1.0)
        if (newCardValue != card1value &&
                newCardValue != card2value &&
                newCardValue != card3value &&
                newCardValue != card4value) {
            win=true
        }
        winlose()
        jaipas.setImageResource(hiddenCard.image)
        jai.alpha=RedOrBlackApp.masked
    }

    private fun makeBackGroundClickableAfterXsec(sec : Double)
    {
        mycard1.isEnabled=false
        mycard2.isEnabled=false
        mycard3.isEnabled=false
        mycard4.isEnabled=false
        jai.isEnabled=false
        jaipas.isEnabled=false


        mycard1.isClickable=false
        mycard2.isClickable=false
        mycard3.isClickable=false
        mycard4.isClickable=false
        jai.isClickable=false
        jaipas.isClickable=false

        val that = this
        object : CountDownTimer((sec*1000).toLong(), 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                if (RedOrBlackApp.getPlayerForPhase(phase) == null)
                {
                    val intent = Intent(that, EndGameActivity::class.java)
                    layout_phase5.setOnClickListener(
                            {
                                startActivity(intent)
                                finish()
                            })
                }
                else
                {
                    val intent = Intent(that, Phase5Activity::class.java)
                    layout_phase5.setOnClickListener(
                            {
                                startActivity(intent)
                                finish()
                            })
                }
            }
        }.start()
    }

    private fun winlose()
    {
        questionmark.visibility=View.INVISIBLE
        val sips = if (win) RedOrBlackApp.rules.phase5sipsgiven else RedOrBlackApp.rules.phase5sipsdrunk
        tv_winorlose.visibility=View.VISIBLE
        tv_drinkorgive.visibility=View.VISIBLE
        tv_winorlose.text = if(win) getString(R.string.win) else getString(R.string.lose)
        when(sips)
        {
            0   -> tv_drinkorgive.visibility=View.INVISIBLE
            1   -> tv_drinkorgive.text = if(win) getString(R.string.give1,sips) else getString(R.string.drink1,sips)
            else-> tv_drinkorgive.text = if(win) getString(R.string.give,sips)  else getString(R.string.drink,sips)
        }
        if (win)
            joueur.given=joueur.given+sips
        else
            joueur.drunk=joueur.drunk+sips
        RedOrBlackApp.history.add(CardPickedEvent(joueur, hiddenCard, win, sips))
    }
}
