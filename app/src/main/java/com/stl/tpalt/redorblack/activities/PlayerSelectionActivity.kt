package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageButton
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.Player
import com.stl.tpalt.redorblack.model.RedOrBlackApp
import com.stl.tpalt.redorblack.utils.PlayerListAdapter
import kotlinx.android.synthetic.main.activity_player_selection.*
import org.jetbrains.anko.toast


//can go to StartGameActivity from here
class PlayerSelectionActivity : AppCompatActivity() {

    private val playerList: MutableList<Player> = RedOrBlackApp.players
    private lateinit var buttonSettings : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_selection)

        val isTablet = resources.getBoolean(R.bool.isTablet)

        requestedOrientation = if (isTablet) {
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        }
        else
        {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        val adapter = PlayerListAdapter(this, playerList)
        listview_playerlist.adapter = adapter

        button_startgame.setOnClickListener {
            if (RedOrBlackApp.players.size < 1)
            {
                toast(R.string.emptyplayerlist)
                return@setOnClickListener
            }
            val intent = Intent(this, StartGameActivity::class.java)
            intent.putExtra("nbJeuCartes", number_picker.value)
            startActivity(intent)
            onPause()
        }

        button_add.setOnClickListener {
            val newplayername : String = input_playername.text.toString().trim().replace("\\s+".toRegex(), " ")
            if (newplayername == "")
                return@setOnClickListener
            if (playerList.any { p -> p.name.toLowerCase()==newplayername.toLowerCase() }) {
                toast(R.string.playeralreadyexists)
                return@setOnClickListener
            }
            else
                input_playername.setText("")
            playerList.add(Player(newplayername))
            adapter.notifyDataSetChanged()
            updateNumberPicker()
        }

        buttonSettings = findViewById(R.id.settings)
        buttonSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        input_playername.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                button_add.isEnabled=!p0.isNullOrBlank()
            }
        })
    }

    //need to be called after changing (add / suppress) list of players
    private fun updateNumberPicker() {
        number_picker.maxValue=Math.max(1, playerList.size)
        number_picker.wrapSelectorWheel = false
        number_picker.minValue=playerList.size/8+1
        number_picker.wrapSelectorWheel = false
    }

}
