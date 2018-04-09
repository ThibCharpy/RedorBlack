package com.stl.tpalt.redorblack.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.*
import com.stl.tpalt.redorblack.utils.AppLogListAdapter
import kotlinx.android.synthetic.main.activity_phase3.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.random_event.*
import kotlin.math.max
import kotlin.math.min

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
    private lateinit var joueur : Player
    private var multiplier : Int = 1

    val adapter = AppLogListAdapter(this, RedOrBlackApp.logs)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase3)

        val isTablet = resources.getBoolean(R.bool.isTablet)

        if (isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            val listview = findViewById<ListView>(R.id.listview_loglist)
            listview.adapter = adapter
        }
        else
        {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
            if (Math.random() < RedOrBlackApp.rules.randomFrequence) {
                random_layout.visibility = View.VISIBLE
                random_layout.setOnClickListener {
                    random_layout.visibility = View.GONE
                }
                multiplier = 2
            }

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
            joueur=playerCurr
            tv_header.text = playerCurr.name
            tv_sub_header.text=getString(R.string.help_phase3)

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
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onInterClicked(v : View)
    {
        hiddenCard = RedOrBlackApp.pickCardFromDeck()
        joueur.cartes[2]=hiddenCard
        newcardValue=hiddenCard.getValue()
        makeBackGroundClickableAfterXsec(1.0)
        val min = min(card1value, card2value)
        val max = max(card1value, card2value)
        if (newcardValue in (min + 1)..(max - 1)) {
            win=true
        }
        winlose()
        interCard.setImageResource(hiddenCard.image)
        exterCard.setImageResource(R.drawable.verso)
        equalsCard.setImageResource(R.drawable.verso)

        logWhatHappened(hiddenCard.image)
    }

    @Suppress("UNUSED_PARAMETER")
    fun onEqualsClicked(v : View)
    {
        hiddenCard = RedOrBlackApp.pickCardFromDeck()
        joueur.cartes[2]=hiddenCard
        newcardValue=hiddenCard.getValue()
        makeBackGroundClickableAfterXsec(1.0)
        if (card1value == newcardValue || card2value == newcardValue) {
            win=true
            bojeu=true
        }
        winlose(win)
        equalsCard.setImageResource(hiddenCard.image)
        interCard.setImageResource(R.drawable.verso)
        exterCard.setImageResource(R.drawable.verso)

        logWhatHappened(hiddenCard.image)
    }

    @Suppress("UNUSED_PARAMETER")
    fun onExterClicked(v : View)
    {
        hiddenCard = RedOrBlackApp.pickCardFromDeck()
        joueur.cartes[2]=hiddenCard
        newcardValue=hiddenCard.getValue()
        makeBackGroundClickableAfterXsec(1.0)
        val min = min(card1value, card2value)
        val max = max(card1value, card2value)
        if (newcardValue > max || newcardValue < min) {
            win=true
        }
        winlose()
        exterCard.setImageResource(hiddenCard.image)
        interCard.setImageResource(R.drawable.verso)
        equalsCard.setImageResource(R.drawable.verso)

        logWhatHappened(hiddenCard.image)
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
                    layout_phase3.setOnClickListener(
                            {
                                startActivity(intent)
                                finish()
                            })
                }
                else
                {
                    val intent = Intent(that, Phase3Activity::class.java)
                    layout_phase3.setOnClickListener(
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
        var sips = if (win) RedOrBlackApp.rules.phase3sipsgiven else RedOrBlackApp.rules.phase3sipsdrunk
        val bonussips = if (bonus) RedOrBlackApp.rules.bonusForEquals else 0
        tv_winorlose.visibility=View.VISIBLE
        tv_drinkorgive.visibility=View.VISIBLE
        tv_winorlose.text = if(bojeu) getString(R.string.bojeu) else if(win) getString(R.string.win) else getString(R.string.lose)
        sips += bonussips
        sips *= multiplier
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

    fun logWhatHappened(cardId : Int){
        var sips = if (win) RedOrBlackApp.rules.phase1sipsgiven else RedOrBlackApp.rules.phase1sipsdrunk
        sips *= multiplier
        when(sips)
        {
            1   -> if(win)
                addLog("3: "+joueur.name+" "+getString(R.string.give1,sips).toLowerCase(),cardId)
            else
                addLog("3: "+joueur.name+" "+getString(R.string.drink1,sips).toLowerCase(),cardId)
            else-> if(win)
                addLog("3: "+joueur.name+" "+getString(R.string.give,sips).toLowerCase(),cardId)
            else
                addLog("3: "+joueur.name+" "+getString(R.string.drink,sips).toLowerCase(),cardId)
        }
    }

    @SuppressLint("NewApi")
    fun addLog(text : String, cardId: Int){
        RedOrBlackApp.logs.reverse()
        RedOrBlackApp.logs.add(GameLog(text,cardId))
        RedOrBlackApp.logs.reverse()
        adapter.notifyDataSetChanged()
    }
}
