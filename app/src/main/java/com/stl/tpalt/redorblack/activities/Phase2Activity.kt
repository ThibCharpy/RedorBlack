package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import android.widget.LinearLayout
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.Card
import com.stl.tpalt.redorblack.model.RedOrBlackApp
import kotlinx.android.synthetic.main.activity_phase2.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast





class Phase2Activity : AppCompatActivity() {

    lateinit var lessCard: ImageView
    lateinit var moreCard: ImageView
    lateinit var equalsCard: ImageView
    lateinit var firstCard: ImageView
    lateinit var hiddenCard : Card
    private var card1value : Int = -1
    private var newcardValue: Int = -1
    private var win : Boolean = false
    private var bojeu : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase2)
        lessCard=phase2_card_less
        moreCard=phase2_card_more
        equalsCard=phase2_card_equals
        firstCard=phase2_card_mycard

        val playerCurr = RedOrBlackApp.getPlayerForPhase(2)
        if (playerCurr == null)
        {
            val intent = Intent(this, Phase1Activity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            tv_header.text = playerCurr.name
            hiddenCard = RedOrBlackApp.pickCardFromDeck()
            playerCurr.cartes[1] = hiddenCard

            //UI init
            lessCard.setImageResource(R.drawable.minus)
            moreCard.setImageResource(R.drawable.plus)
            equalsCard.setImageResource(R.drawable.equals)
            firstCard.setImageResource(playerCurr.cartes[0]!!.image)
            card1value = playerCurr.cartes[0]!!.getValue()
            newcardValue = hiddenCard.getValue()
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onLessClicked(v : View)
    {
        makeBackGroundClickableAfterXsec(1)
        if (card1value > newcardValue)
            win=true
        winlose()
        lessCard.setImageResource(hiddenCard.image)
        val margin = resources.getDimension(R.dimen.maincardsmargin).toInt()
        MarginLayoutParams(lessCard.layoutParams).setMargins(margin,margin,margin,margin)
    }
    @Suppress("UNUSED_PARAMETER")
    fun onMoreClicked(v : View)
    {
        makeBackGroundClickableAfterXsec(1)
        if (card1value < newcardValue)
            win=true
        winlose()
        moreCard.setImageResource(hiddenCard.image)
    }
    @Suppress("UNUSED_PARAMETER")
    fun onEqualsClicked(v : View)
    {
        makeBackGroundClickableAfterXsec(1)
        if (card1value == newcardValue) {
            win=true
            bojeu=true
        }
        winlose()
        equalsCard.setImageResource(hiddenCard.image)
    }
    @Suppress("UNUSED_PARAMETER")
    fun goToPhase3(v : View)
    {
        val intent = Intent(this, Phase3Activity::class.java)
        startActivity(intent)
        finish()
    }

    fun makeBackGroundClickableAfterXsec(sec : Long)
    {
        phase2_card_less.isEnabled=false
        phase2_card_equals.isEnabled=false
        phase2_card_more.isEnabled=false
        phase2_card_mycard.isEnabled=false


        phase2_card_less.isClickable=false
        phase2_card_equals.isClickable=false
        phase2_card_more.isClickable=false
        phase2_card_mycard.isClickable=false

        val phase2Activy = this
        object : CountDownTimer((sec*1000), 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                toast("Go")
                val intent = Intent(phase2Activy, Phase2Activity::class.java)
                layout_phase2.setOnClickListener({ _ ->
                    startActivity(intent)
                    finish()
                })
            }
        }.start()
    }

    private fun winlose()
    {
        questionmark.visibility=View.INVISIBLE
        val sips = RedOrBlackApp.rules.phase2sips
        val bonussips = RedOrBlackApp.rules.bonusForEquals
        tv_winorlose.visibility=View.VISIBLE
        tv_drinkorgive.visibility=View.VISIBLE
        tv_winorlose.text = if(bojeu) getString(R.string.bojeu) else if(win) getString(R.string.win) else getString(R.string.lose)
        when(sips)
        {
            0   -> tv_drinkorgive.visibility=View.INVISIBLE
            1   -> tv_drinkorgive.text = if(win) getString(R.string.give1,sips) else getString(R.string.drink1,sips)
            else-> tv_drinkorgive.text = if(win) getString(R.string.give,sips)  else getString(R.string.drink,sips)
        }
    }
}

