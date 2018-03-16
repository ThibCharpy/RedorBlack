package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.Card
import com.stl.tpalt.redorblack.model.RedOrBlackApp
import kotlinx.android.synthetic.main.activity_phase3.*
import kotlinx.android.synthetic.main.header.*
import kotlin.math.*

@Suppress("MemberVisibilityCanBePrivate")
class Phase3Activity : AppCompatActivity() {
    val phase : Int = 3

    lateinit var firstCard : ImageView
    lateinit var secondCard : ImageView
    lateinit var interCard  : ImageView
    lateinit var equalsCard : ImageView
    lateinit var exterCard : ImageView
    private var card1value : Int = -1
    private var card2value : Int = -1
    private var newcardValue: Int = -1
    private var win : Boolean = false
    private var bojeu : Boolean = false
    lateinit var hiddenCard : Card

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase3)
        interCard=phase3_card_inter
        equalsCard=phase3_card_equals
        exterCard=phase3_card_exter

        val playerCurr = RedOrBlackApp.getPlayerForPhase(phase)
        if (playerCurr == null)
        {
            val intent = Intent(this, Phase4Activity::class.java)
            startActivity(intent)
            finish()
        }
        else
        {
            tv_header.text = playerCurr.name
            hiddenCard = RedOrBlackApp.pickCardFromDeck()
            playerCurr.cartes[2]=hiddenCard

            //rebinding to make card value crescent
            if ((playerCurr.cartes[0]!!.getValue() > playerCurr.cartes[1]!!.getValue())) {
                firstCard=phase3_card_mycard2
                secondCard=phase3_card_mycard1
            }
            else
            {
                firstCard=phase3_card_mycard1
                secondCard=phase3_card_mycard2
            }

            //UI init
            firstCard.setImageResource(playerCurr.cartes[0]!!.image)
            secondCard.setImageResource(playerCurr.cartes[1]!!.image)
            interCard.setImageResource(R.drawable.inter)
            equalsCard.setImageResource(R.drawable.equals)
            exterCard.setImageResource(R.drawable.exter)
            tv_drinkorgive.visibility=View.INVISIBLE
            tv_winorlose.visibility=View.INVISIBLE

            card1value=playerCurr.cartes[0]!!.getValue()
            card2value=playerCurr.cartes[1]!!.getValue()
            newcardValue=hiddenCard.getValue()
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onInterClicked(v : View)
    {
        makeBackGroundClickableAfterXsec(1.0)
        val min = min(card1value, card2value)
        val max = max(card1value, card2value)
        if (newcardValue in (min + 1)..(max - 1)) {
            win=true
        }
        winlose()
        interCard.setImageResource(hiddenCard.image)
        exterCard.alpha=RedOrBlackApp.masked
        equalsCard.alpha=RedOrBlackApp.masked
    }
    @Suppress("UNUSED_PARAMETER")
    fun onEqualsClicked(v : View)
    {
        makeBackGroundClickableAfterXsec(1.0)
        if (card1value == newcardValue) {
            win=true
            bojeu=true
        }
        winlose()
        equalsCard.setImageResource(hiddenCard.image)
        interCard.alpha=RedOrBlackApp.masked
        exterCard.alpha=RedOrBlackApp.masked
    }
    @Suppress("UNUSED_PARAMETER")
    fun onExterClicked(v : View)
    {
        makeBackGroundClickableAfterXsec(1.0)
        val min = min(card1value, card2value)
        val max = max(card1value, card2value)
        if (newcardValue > max || newcardValue < min) {
            win=true
        }
        winlose()
        exterCard.setImageResource(hiddenCard.image)
        interCard.alpha=RedOrBlackApp.masked
        equalsCard.alpha=RedOrBlackApp.masked
    }
    @Suppress("UNUSED_PARAMETER")
    fun next(v : View)
    {
        val intent = Intent(this, Phase3Activity::class.java)
        startActivity(intent)
        finish()
    }

    fun makeBackGroundClickableAfterXsec(sec : Double)
    {
        interCard.isEnabled=false
        equalsCard.isEnabled=false
        exterCard.isEnabled=false
        firstCard.isEnabled=false
        secondCard.isEnabled=false

        interCard.isClickable=false
        equalsCard.isClickable=false
        exterCard.isClickable=false
        firstCard.isClickable=false
        secondCard.isClickable=false

        val that = this
        object : CountDownTimer((sec*1000).toLong(), 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                if (RedOrBlackApp.getPlayerForPhase(phase) == null)
                {
                    val intent = Intent(that, Phase4Activity::class.java)
                    layout_phase3.setOnClickListener({ _ ->
                        startActivity(intent)
                        finish()
                    })
                }
                else
                {
                    val intent = Intent(that, Phase3Activity::class.java)
                    layout_phase3.setOnClickListener({ _ ->
                        startActivity(intent)
                        finish()
                    })
                }
            }
        }.start()
    }

    private fun winlose(bonus : Boolean = false)
    {
        questionmark.visibility=View.INVISIBLE
        var sips = RedOrBlackApp.rules.phase3sips
        val bonussips = if (bonus) RedOrBlackApp.rules.bonusForEquals else 0
        tv_winorlose.visibility=View.VISIBLE
        tv_drinkorgive.visibility=View.VISIBLE
        tv_winorlose.text = if(bojeu) getString(R.string.bojeu) else if(win) getString(R.string.win) else getString(R.string.lose)
        sips+=bonussips
        when(sips)
        {
            0   -> tv_drinkorgive.visibility=View.INVISIBLE
            1   -> tv_drinkorgive.text = if(win) getString(R.string.give1,sips) else getString(R.string.drink1,sips)
            else-> tv_drinkorgive.text = if(win) getString(R.string.give,sips)  else getString(R.string.drink,sips)
        }
    }
}
