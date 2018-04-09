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
import kotlinx.android.synthetic.main.activity_phase4.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.random_event.*

class Phase4Activity : AppCompatActivity() {
    val phase : Int = 4

    private lateinit var spadeCard : ImageView
    private lateinit var heartCard : ImageView
    private lateinit var diamondCard : ImageView
    private lateinit var clubCard : ImageView
    private var win : Boolean = false
    private lateinit var hiddenCard : Card
    private var multiplier : Int = 1
    private lateinit var joueur : Player

    val adapter = AppLogListAdapter(this, RedOrBlackApp.logs)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase4)

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
            joueur=playerCurr
            tv_header.text = playerCurr.name
            tv_sub_header.text=getString(R.string.help_phase4)
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
        hiddenCard = RedOrBlackApp.pickCardFromDeck()
        joueur.cartes[3]=hiddenCard

        makeBackGroundClickableAfterXsec(1.0)
        if (hiddenCard.cardname[0] == 's')
            win=true
        winlose()
        spadeCard.setImageResource(hiddenCard.image)
        heartCard.setImageResource(R.drawable.verso)
        diamondCard.setImageResource(R.drawable.verso)
        clubCard.setImageResource(R.drawable.verso)

        logWhatHappened(hiddenCard.image)
    }
    @Suppress("UNUSED_PARAMETER")
    fun onHeartClicked(v : View)
    {
        hiddenCard = RedOrBlackApp.pickCardFromDeck()
        joueur.cartes[3]=hiddenCard

        makeBackGroundClickableAfterXsec(1.0)
        if (hiddenCard.cardname[0] == 'h')
            win=true
        winlose()
        heartCard.setImageResource(hiddenCard.image)
        spadeCard.setImageResource(R.drawable.verso)
        diamondCard.setImageResource(R.drawable.verso)
        clubCard.setImageResource(R.drawable.verso)

        logWhatHappened(hiddenCard.image)
    }
    @Suppress("UNUSED_PARAMETER")
    fun onDiamondClicked(v : View)
    {
        hiddenCard = RedOrBlackApp.pickCardFromDeck()
        joueur.cartes[3]=hiddenCard

        makeBackGroundClickableAfterXsec(1.0)
        if (hiddenCard.cardname[0] == 'd')
            win=true
        winlose()
        diamondCard.setImageResource(hiddenCard.image)
        spadeCard.setImageResource(R.drawable.verso)
        heartCard.setImageResource(R.drawable.verso)
        clubCard.setImageResource(R.drawable.verso)

        logWhatHappened(hiddenCard.image)
    }
    @Suppress("UNUSED_PARAMETER")
    fun onClubClicked(v : View)
    {
        hiddenCard = RedOrBlackApp.pickCardFromDeck()
        joueur.cartes[3]=hiddenCard

        makeBackGroundClickableAfterXsec(1.0)
        if (hiddenCard.cardname[0] == 'c')
            win=true
        winlose()
        clubCard.setImageResource(hiddenCard.image)
        spadeCard.setImageResource(R.drawable.verso)
        heartCard.setImageResource(R.drawable.verso)
        diamondCard.setImageResource(R.drawable.verso)

        logWhatHappened(hiddenCard.image)
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
                    layout_phase4.setOnClickListener(
                            {
                                startActivity(intent)
                                finish()
                            })
                }
                else
                {
                    val intent = Intent(that, Phase4Activity::class.java)
                    layout_phase4.setOnClickListener(
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
        var sips = if (win) RedOrBlackApp.rules.phase4sipsgiven else RedOrBlackApp.rules.phase4sipsdrunk
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
                addLog("4: "+joueur.name+" "+getString(R.string.give1,sips).toLowerCase(),cardId)
            else
                addLog("4: "+joueur.name+" "+getString(R.string.drink1,sips).toLowerCase(),cardId)
            else-> if(win)
                addLog("4: "+joueur.name+" "+getString(R.string.give,sips).toLowerCase(),cardId)
            else
                addLog("4: "+joueur.name+" "+getString(R.string.drink,sips).toLowerCase(),cardId)
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
