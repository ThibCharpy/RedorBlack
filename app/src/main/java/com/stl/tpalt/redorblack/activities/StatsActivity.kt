package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.RedOrBlackApp
import kotlinx.android.synthetic.main.activity_stats.*

class StatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        val players = RedOrBlackApp.players

        var playermostdrank = players[0].name
        var numbermostdrank = players[0].drunk
        var playerleastdrank = players[0].name
        var numberleastdrank = players[0].drunk
        var playermostgiven = players[0].name
        var numbermostgiven = players[0].given
        var playerleastgiven = players[0].name
        var numberleastgiven = players[0].given

        players.forEach { p ->
            if (p.drunk > numbermostdrank)
            {
                numbermostdrank = p.drunk
                playermostdrank = p.name
            }
            if (p.drunk < numberleastdrank)
            {
                numberleastdrank = p.drunk
                playerleastdrank = p.name
            }
            if (p.given > numbermostgiven)
            {
                numbermostgiven = p.given
                playermostgiven = p.name
            }
            if (p.given < numberleastgiven)
            {
                numberleastgiven = p.given
                playerleastgiven = p.name
            }
        }
        mostdrunkanswer.text = if (numbermostdrank>1)
            getString(R.string.mostdrunkanswer, playermostdrank, numbermostdrank)
        else
            getString(R.string.mostdrunkanswer1, playermostdrank, numbermostdrank)

        mostgivenanswer.text = if (numbermostgiven>1)
            getString(R.string.mostgivenanswer, playermostgiven, numbermostgiven)
        else
            getString(R.string.mostgivenanswer1, playermostgiven, numbermostgiven)

        leastdrunkanswer.text = if (numberleastdrank>1)
            getString(R.string.leastdrunkanswer, playerleastdrank, numberleastdrank)
        else
            getString(R.string.leastdrunkanswer1, playerleastdrank, numberleastdrank)

        leastgivenanswer.text = if (numberleastgiven>1)
            getString(R.string.leastgivenanswer, playerleastgiven, numberleastgiven)
        else
            getString(R.string.leastgivenanswer1, playerleastgiven, numberleastgiven)


        stats_replay.setOnClickListener {
            RedOrBlackApp.players.forEach { p ->
                p.cartes = arrayOfNulls(5)
            }
            val intent = Intent(this, PlayerSelectionActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
