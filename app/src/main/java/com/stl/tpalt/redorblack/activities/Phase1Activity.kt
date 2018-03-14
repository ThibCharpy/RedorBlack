package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.Card
import com.stl.tpalt.redorblack.model.RedOrBlackApp
import com.stl.tpalt.redorblack.utils.CardImage
import kotlinx.android.synthetic.main.activity_phase1.*
import org.jetbrains.anko.toast

class Phase1Activity : AppCompatActivity() {

    lateinit var hiddenCard : Card
    lateinit var redCard : CardImage
    lateinit var blackCard : CardImage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase1)

        redCard = phase1_card_red
        blackCard = phase1_card_black
        hiddenCard = RedOrBlackApp.pickCardFromDeck()

        redCard.setImageResource(R.drawable.red)
        blackCard.setImageResource(R.drawable.black)

    }

    fun redClicked(v : View)
    {
        //TODO : show card and reduce other opacity
        toast("red clicked")
        makeBackGroundClickableAfterXsec(3)
        val win = when (hiddenCard.cardname[0]) {
            'd', 'h' -> true
            else -> false
        }
        redCard.setImageResource(hiddenCard.image)

        /**
         * Resources res = getResources();
         * String text = String.format(res.getString(R.string.welcome_messages), username, mailCount);
         * https://stackoverflow.com/questions/3656371/dynamic-string-using-string-xml
         */

        if (win) {
            //TODO: Afficher le text view correspondant "Gagné tu donne une gorgée"
        }else{
            //TODO: Afficher le text view correspondant "Gagné tu bois une gorgée"
        }
    }

    fun blackClicked(v : View)
    {
        //TODO : show card and reduce other opacity
        toast("black clicked")
        makeBackGroundClickableAfterXsec(3)
        toast("red clicked")
        makeBackGroundClickableAfterXsec(3)
        val win = when (hiddenCard.cardname[0]) {
            's', 'c' -> true
            else -> false
        }
        blackCard.setImageResource(hiddenCard.image)

        if (win) {
            //TODO: Afficher le text view correspondant R.string.loose
        }else{
            //TODO: Afficher le text view correspondant R.string.win
        }
    }

    fun goToPhase2(v : View)
    {
        val intent = Intent(this, Phase2Activy::class.java)
        startActivity(intent)
        finish()
    }

    fun makeBackGroundClickableAfterXsec(sec : Long)
    {
        phase1_card_red.isEnabled=false
        phase1_card_black.isEnabled=false
        object : CountDownTimer(sec*1000, 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                layout_phase1.isClickable=true;
                toast("bg clickable")
            }
        }.start()
    }
}
