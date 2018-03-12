package com.stl.tpalt.redorblack

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by I346992 on 10/03/2018.
 */
class PlayerList() : Parcelable{
    var pl_list : ArrayList<Player> = arrayListOf()

    constructor(parcel: Parcel) : this()

    fun addPlayer(playername: String): Unit {
        pl_list.add(Player(playername))
    }

    fun size() :Int {
        return pl_list.size
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {}

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlayerList> {
        override fun createFromParcel(parcel: Parcel): PlayerList {
            return PlayerList(parcel)
        }

        override fun newArray(size: Int): Array<PlayerList?> {
            return arrayOfNulls(size)
        }
    }
}