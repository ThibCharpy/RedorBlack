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
import kotlinx.android.synthetic.main.activity_phase5.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.random_event.*

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
    private var multiplier : Int = 1

    private lateinit var joueur : Player

    val adapter = AppLogListAdapter(this, RedOrBlackApp.logs)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase5)

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

        val playerCurr = RedOrBlackApp.getPlayerForPhase(phase)
        if (playerCurr == null)
        {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            joueur=playerCurr
            tv_header.text = playerCurr.name
            tv_sub_header.text=getString(R.string.help_phase5)

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
        jaipas.setImageResource(R.drawable.verso)

        logWhatHappened(hiddenCard.image)
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
        jai.setImageResource(R.drawable.verso)

        logWhatHappened(hiddenCard.image)
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
                    val intent = Intent(that, StatsActivity::class.java)
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
        var sips = if (win) RedOrBlackApp.rules.phase5sipsgiven else RedOrBlackApp.rules.phase5sipsdrunk
        tv_winorlose.visibility=View.VISIBLE
        tv_drinkorgive.visibility=View.VISIBLE
        tv_winorlose.text = if(win) getString(R.string.win) else getString(R.string.lose)
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

    private fun logWhatHappened(cardId : Int){
        var sips = if (win) RedOrBlackApp.rules.phase1sipsgiven else RedOrBlackApp.rules.phase1sipsdrunk
        sips *= multiplier
        when(sips)
        {
            1   -> if(win)
                addLog("5: "+joueur.name+" "+getString(R.string.give1,sips).toLowerCase(),cardId)
            else
                addLog("5: "+joueur.name+" "+getString(R.string.drink1,sips).toLowerCase(),cardId)
            else-> if(win)
                addLog("5: "+joueur.name+" "+getString(R.string.give,sips).toLowerCase(),cardId)
            else
                addLog("5: "+joueur.name+" "+getString(R.string.drink,sips).toLowerCase(),cardId)
        }
    }

    @SuppressLint("NewApi")
    private fun addLog(text : String, cardId: Int){
        RedOrBlackApp.logs.reverse()
        RedOrBlackApp.logs.add(GameLog(text,cardId))
        RedOrBlackApp.logs.reverse()
        adapter.notifyDataSetChanged()
    }
}
