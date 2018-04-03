package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.Card
import com.stl.tpalt.redorblack.model.CardPickedEvent
import com.stl.tpalt.redorblack.model.Player
import com.stl.tpalt.redorblack.model.RedOrBlackApp
import kotlinx.android.synthetic.main.activity_phase2.*
import kotlinx.android.synthetic.main.header.*


@Suppress("MemberVisibilityCanBePrivate")
class Phase2Activity : AppCompatActivity() {

    val phase : Int = 2
    lateinit var lessCard: ImageView
    lateinit var moreCard: ImageView
    lateinit var equalsCard: ImageView
    lateinit var firstCard: ImageView
    lateinit var hiddenCard : Card
    private var card1value : Int = -1
    private var newcardValue: Int = -1
    private var win : Boolean = false
    private var bojeu : Boolean = false

    private lateinit var joueur : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase2)
        lessCard=phase2_card_less
        moreCard=phase2_card_more
        equalsCard=phase2_card_equals
        firstCard=phase2_card_mycard

        val playerCurr = RedOrBlackApp.getPlayerForPhase(phase)
        if (playerCurr == null)
        {
            val intent = Intent(this, Phase3Activity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            joueur=playerCurr
            tv_header.text = playerCurr.name

            //UI init
            lessCard.setImageResource(R.drawable.minus)
            moreCard.setImageResource(R.drawable.plus)
            equalsCard.setImageResource(R.drawable.equals)
            firstCard.setImageResource(playerCurr.cartes[0]!!.image)
            card1value = playerCurr.cartes[0]!!.getValue()
            tv_drinkorgive.visibility=View.INVISIBLE
            tv_winorlose.visibility=View.INVISIBLE
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onLessClicked(v : View)
    {
        hiddenCard = RedOrBlackApp.pickCardFromDeck()
        joueur.cartes[1] = hiddenCard
        newcardValue = hiddenCard.getValue()
        makeBackGroundClickableAfterXsec(1.0)
        if (card1value > newcardValue)
            win=true
        winlose()
        lessCard.setImageResource(hiddenCard.image)
        moreCard.alpha=RedOrBlackApp.masked
        equalsCard.alpha=RedOrBlackApp.masked
        val margin = resources.getDimension(R.dimen.maincardsmargin).toInt()
        MarginLayoutParams(lessCard.layoutParams).setMargins(margin,margin,margin,margin)
    }
    @Suppress("UNUSED_PARAMETER")
    fun onMoreClicked(v : View)
    {
        hiddenCard = RedOrBlackApp.pickCardFromDeck()
        joueur.cartes[1] = hiddenCard
        newcardValue = hiddenCard.getValue()
        makeBackGroundClickableAfterXsec(1.0)
        if (card1value < newcardValue)
            win=true
        winlose()
        moreCard.setImageResource(hiddenCard.image)
        lessCard.alpha=RedOrBlackApp.masked
        equalsCard.alpha=RedOrBlackApp.masked
    }
    @Suppress("UNUSED_PARAMETER")
    fun onEqualsClicked(v : View)
    {
        hiddenCard = RedOrBlackApp.pickCardFromDeck()
        joueur.cartes[1] = hiddenCard

        makeBackGroundClickableAfterXsec(1.0)
        if (card1value == newcardValue) {
            win=true
            bojeu=true
        }
        winlose(win)
        equalsCard.setImageResource(hiddenCard.image)
        lessCard.alpha=RedOrBlackApp.masked
        moreCard.alpha=RedOrBlackApp.masked
    }

    fun makeBackGroundClickableAfterXsec(sec : Double)
    {
        phase2_card_less.isEnabled=false
        phase2_card_equals.isEnabled=false
        phase2_card_more.isEnabled=false
        phase2_card_mycard.isEnabled=false

        phase2_card_less.isClickable=false
        phase2_card_equals.isClickable=false
        phase2_card_more.isClickable=false
        phase2_card_mycard.isClickable=false

        val that = this
        object : CountDownTimer((sec*1000).toLong(), 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                if (RedOrBlackApp.getPlayerForPhase(phase) == null)
                {
                    val intent = Intent(that, Phase3Activity::class.java)
                    layout_phase2.setOnClickListener(
                            {
                                startActivity(intent)
                                finish()
                            })
                }
                else
                {
                    val intent = Intent(that, Phase2Activity::class.java)
                    layout_phase2.setOnClickListener(
                            {
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
        var sips = if (win) RedOrBlackApp.rules.phase2sipsgiven else RedOrBlackApp.rules.phase2sipsdrunk
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
        if (win)
            joueur.given=joueur.given+sips
        else
            joueur.drunk=joueur.drunk+sips
        RedOrBlackApp.history.add(CardPickedEvent(joueur, hiddenCard, win, sips))
    }
}

