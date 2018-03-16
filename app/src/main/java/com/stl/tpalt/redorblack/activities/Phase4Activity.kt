package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.Card
import com.stl.tpalt.redorblack.model.RedOrBlackApp
import kotlinx.android.synthetic.main.activity_phase4.*
import kotlinx.android.synthetic.main.header.*

class Phase4Activity : AppCompatActivity() {
    val phase : Int = 4

    lateinit var spadeCard : ImageView
    lateinit var heartCard : ImageView
    lateinit var diamondCard : ImageView
    lateinit var clubCard : ImageView
    private var win : Boolean = false
    lateinit var hiddenCard : Card

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase4)
        spadeCard=phase4_card_spade
        heartCard=phase4_card_heart
        diamondCard=phase4_card_diamond
        clubCard=phase4_card_club
        val playerCurr = RedOrBlackApp.getPlayerForPhase(phase)
        if (playerCurr == null)
        {
            val intent = Intent(this, Phase5Activity::class.java)
            startActivity(intent)
            finish()
        }
        else
        {
            tv_header.text = playerCurr.name
            hiddenCard = RedOrBlackApp.pickCardFromDeck()
            playerCurr.cartes[3]=hiddenCard
        }

        //UI init
        spadeCard.setImageResource(R.drawable.sbig)
        heartCard.setImageResource(R.drawable.hbig)
        diamondCard.setImageResource(R.drawable.dbig)
        clubCard.setImageResource(R.drawable.cbig)
        tv_drinkorgive.visibility= View.INVISIBLE
        tv_winorlose.visibility= View.INVISIBLE
    }
    @Suppress("UNUSED_PARAMETER")
    fun onSpadeClicked(v : View)
    {
        makeBackGroundClickableAfterXsec(1.0)
        if (hiddenCard.cardname[0] == 's')
            win=true
        winlose()
        spadeCard.setImageResource(hiddenCard.image)
        heartCard.alpha=RedOrBlackApp.masked
        diamondCard.alpha=RedOrBlackApp.masked
        clubCard.alpha=RedOrBlackApp.masked
    }
    @Suppress("UNUSED_PARAMETER")
    fun onHeartClicked(v : View)
    {
        makeBackGroundClickableAfterXsec(1.0)
        if (hiddenCard.cardname[0] == 'h')
            win=true
        winlose()
        heartCard.setImageResource(hiddenCard.image)
        spadeCard.alpha=RedOrBlackApp.masked
        diamondCard.alpha=RedOrBlackApp.masked
        clubCard.alpha=RedOrBlackApp.masked
    }
    @Suppress("UNUSED_PARAMETER")
    fun onDiamondClicked(v : View)
    {
        makeBackGroundClickableAfterXsec(1.0)
        if (hiddenCard.cardname[0] == 'd')
            win=true
        winlose()
        diamondCard.setImageResource(hiddenCard.image)
        spadeCard.alpha=RedOrBlackApp.masked
        heartCard.alpha=RedOrBlackApp.masked
        clubCard.alpha=RedOrBlackApp.masked
    }
    @Suppress("UNUSED_PARAMETER")
    fun onClubClicked(v : View)
    {
        makeBackGroundClickableAfterXsec(1.0)
        if (hiddenCard.cardname[0] == 'c')
            win=true
        winlose()
        clubCard.setImageResource(hiddenCard.image)
        spadeCard.alpha=RedOrBlackApp.masked
        heartCard.alpha=RedOrBlackApp.masked
        diamondCard.alpha=RedOrBlackApp.masked
    }

    private fun makeBackGroundClickableAfterXsec(sec : Double)
    {
        spadeCard.isEnabled=false
        diamondCard.isEnabled=false
        heartCard.isEnabled=false
        clubCard.isEnabled=false

        spadeCard.isClickable=false
        diamondCard.isClickable=false
        heartCard.isClickable=false
        clubCard.isClickable=false

        val that = this
        object : CountDownTimer((sec*1000).toLong(), 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                if (RedOrBlackApp.getPlayerForPhase(phase) == null)
                {
                    val intent = Intent(that, Phase5Activity::class.java)
                    layout_phase4.setOnClickListener({ _ ->
                        startActivity(intent)
                        finish()
                    })
                }
                else
                {
                    val intent = Intent(that, Phase4Activity::class.java)
                    layout_phase4.setOnClickListener({ _ ->
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
        val sips = RedOrBlackApp.rules.phase4sips
        tv_winorlose.visibility=View.VISIBLE
        tv_drinkorgive.visibility=View.VISIBLE
        tv_winorlose.text = if(win) getString(R.string.win) else getString(R.string.lose)
        when(sips)
        {
            0   -> tv_drinkorgive.visibility=View.INVISIBLE
            1   -> tv_drinkorgive.text = if(win) getString(R.string.give1,sips) else getString(R.string.drink1,sips)
            else-> tv_drinkorgive.text = if(win) getString(R.string.give,sips)  else getString(R.string.drink,sips)
        }
    }
}
