package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.Card
import com.stl.tpalt.redorblack.model.Player
import com.stl.tpalt.redorblack.model.RedOrBlackApp
import kotlinx.android.synthetic.main.activity_phase1.*
import kotlinx.android.synthetic.main.header.*

class Phase1Activity : AppCompatActivity() {

    val phase : Int = 1
    var win : Boolean = false

    private lateinit var hiddenCard : Card
    private lateinit var redCard : ImageView
    private lateinit var blackCard : ImageView

    private lateinit var joueur : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase1)

        val playerCurr = RedOrBlackApp.getPlayerForPhase(phase)
        if (playerCurr == null)
        {
            val intent = Intent(this, Phase2Activity::class.java)
            startActivity(intent)
            finish()
        }
        else
        {
            joueur=playerCurr
        }

        redCard = phase1_card_red
        blackCard = phase1_card_black
        tv_header.text=playerCurr!!.name
        hiddenCard = RedOrBlackApp.pickCardFromDeck()
        playerCurr.cartes[0]=hiddenCard

        //UI init
        redCard.setImageResource(R.drawable.red)
        blackCard.setImageResource(R.drawable.black)
        tv_drinkorgive.visibility=View.INVISIBLE
        tv_winorlose.visibility=View.INVISIBLE


    }

    @Suppress("UNUSED_PARAMETER")
    fun redClicked(v : View)
    {
        makeBackGroundClickableAfterXsec(1.0)
        win = when (hiddenCard.cardname[0]) {
            'd', 'h' -> true
            else -> false
        }
        winlose()
        redCard.setImageResource(hiddenCard.image)
        blackCard.alpha=RedOrBlackApp.masked
    }
    @Suppress("UNUSED_PARAMETER")
    fun blackClicked(v : View)
    {
        makeBackGroundClickableAfterXsec(1.0)
        win = when (hiddenCard.cardname[0]) {
            's', 'c' -> true
            else -> false
        }
        winlose()
        blackCard.setImageResource(hiddenCard.image)
        redCard.alpha=RedOrBlackApp.masked
    }

    private fun makeBackGroundClickableAfterXsec(sec: Double)
    {
        val that = this
        redCard.isEnabled=false
        blackCard.isEnabled=false
        redCard.isClickable=false
        blackCard.isClickable=false
        object : CountDownTimer((sec*1000).toLong(), 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                if (RedOrBlackApp.getPlayerForPhase(phase) == null) {
                    val intent = Intent(that, Phase2Activity::class.java)
                    layout_phase1.setOnClickListener({ _ ->
                        startActivity(intent)
                        finish()
                    })
                }
                else
                {
                    val intent = Intent(that, Phase1Activity::class.java)
                    layout_phase1.setOnClickListener({ _ ->
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
        val sips = RedOrBlackApp.rules.phase1sips
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
    }

}
